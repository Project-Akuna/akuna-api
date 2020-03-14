package ph.com.adcpp.models.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ph.com.adcpp.commons.exception.RegCodeNotFoundException;
import ph.com.adcpp.commons.exception.RegCodeUsedException;
import ph.com.adcpp.commons.request.*;
import ph.com.adcpp.commons.response.RegistrationCodeResponse;
import ph.com.adcpp.models.entity.*;
import ph.com.adcpp.models.repository.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Choy
 * @date 12/17/2019.
 */
@Slf4j
@Service
public class RegistrationCodeService {

    private RegistrationCodeRepository codeRepository;
    private ObjectMapper mapper;
    private InventoryService inventoryService;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DepotRepository depotRepository;

    @Autowired
    private ADCRepository adcRepository;

    @Autowired
    private UserRepository userRepository;

    public RegistrationCodeService(RegistrationCodeRepository codeRepository, ObjectMapper mapper, InventoryService inventoryService) {
        this.codeRepository = codeRepository;
        this.mapper = mapper;
        this.inventoryService = inventoryService;
    }

    public List<RegistrationCode> findAll() {
        return codeRepository.findAll();
    }

    public RegistrationCode save(RegistrationCode code) {
        return codeRepository.save(code);
    }

    public List<RegistrationCode> save(List<RegistrationCode> codes) {
        return codeRepository.saveAll(codes);
    }

    public List<RegistrationCodeResponse> convertToList(List<RegistrationCode> codes) {
        log.info("Converting registration codes to response...");

        List<RegistrationCodeResponse> responses = new ArrayList<>();
        codes.forEach(code -> responses.add(mapper.convertValue(code, RegistrationCodeResponse.class)));

        log.info("Codes successfully converted.");
        return responses;
    }

    public List<RegistrationCode> generateCodes(Integer quantity) {
        log.info("Generating [{}] new codes...", quantity);

        List<RegistrationCode> registrationCodes = new ArrayList<>();

        for(int ctr = 0; ctr < quantity; ctr++) {

            RegistrationCode registrationCode = new RegistrationCode();
            registrationCode.setCode(UUID.randomUUID().toString());
            registrationCodes.add(registrationCode);
        }

        log.info("Successfully generated [{}] codes", quantity);
        return codeRepository.saveAll(registrationCodes);
    }

    public RegistrationCode findByCode(String code) {
        return codeRepository.findByCode(code);
    }

    public void sellDepotRegCode(SellDepotCodeRequest request) {
        Depot depot = depotRepository.getOne(request.getDepotId());
        Product product = productRepository.getOne(request.getProductId());

        depot.setRegistrationCodes(generateCodes(request.getQuantity()));
        depot.getRegistrationCodes().forEach(code -> code.setOwnerDepot(depot));

        Inventory inventory = depot.getInventory();
        Integer beginningQty = inventory.getQuantity();
        inventory.setQuantity(inventory.getQuantity() + request.getQuantity());

        depot.setInventory(inventory);
        depotRepository.save(depot);

        UpdateInventoryRequest inventoryRequest = new UpdateInventoryRequest();
        inventoryRequest.setQuantitySold(request.getQuantity());
        inventoryRequest.setSoldTo(depot.getName());
        inventoryRequest.setQuantitySold(request.getQuantity());
        inventoryRequest.setDeliveryQuantity(Math.negateExact(request.getQuantity()));

        inventoryRequest.setSellingPrice(product.getCodePrice().multiply(new BigDecimal(request.getQuantity())));
//        inventoryRequest.getProduct().add(mapper.convertValue(product, ProductRequest.class));
        inventoryRequest.setSoldBy(request.getSoldBy());

        inventoryService.updateInventorySysAdmin(inventoryRequest);
        inventoryService.createHistory(inventoryRequest, inventory, beginningQty, inventory.getQuantity(),
                mapper.convertValue(product, ProductRequest.class));

        inventoryRequest.setDeliveryQuantity(Math.abs(inventoryRequest.getDeliveryQuantity()));
        inventoryService.createAcknowledgementReceipt(inventoryRequest);
    }

    public void sellAdcRegCode(SellDepotCodeRequest request) {
        ADC adc = adcRepository.getOne(request.getDepotId());

        Product product = productRepository.getOne(request.getProductId());

        Depot depot = depotRepository.findByLinkedAccount_Username(request.getSoldBy());
        List<RegistrationCode> registrationCodes = depot.getRegistrationCodes()
                .stream().filter(regcode -> Objects.isNull(regcode.getOwnerAdc())).collect(Collectors.toList());

        adc.setRegistrationCodes(registrationCodes.subList(0, request.getQuantity()));
        adc.getRegistrationCodes().forEach(code -> code.setOwnerAdc(adc));

        Inventory inventory = adc.getInventory();
        Integer beginningQty = inventory.getQuantity();
        inventory.setQuantity(inventory.getQuantity() + request.getQuantity());

        adc.setInventory(inventory);
        adcRepository.save(adc);

        UpdateInventoryRequest inventoryRequest = new UpdateInventoryRequest();
        inventoryRequest.setQuantitySold(request.getQuantity());
        inventoryRequest.setSoldTo(adc.getName());
        inventoryRequest.setQuantitySold(request.getQuantity());
        inventoryRequest.setDeliveryQuantity(Math.negateExact(request.getQuantity()));

        inventoryRequest.setSellingPrice(product.getCodePrice().multiply(new BigDecimal(request.getQuantity())));
//        inventoryRequest.getProduct().add(mapper.convertValue(product, ProductRequest.class));
        inventoryRequest.setSoldBy(request.getSoldBy());
        inventoryRequest.setDepotId(depot.getId());
        inventoryService.updateInventoryDepot(inventoryRequest);
        inventoryService.createHistory(inventoryRequest, inventory, beginningQty, inventory.getQuantity(),
                mapper.convertValue(product, ProductRequest.class));

        inventoryRequest.setDeliveryQuantity(Math.abs(inventoryRequest.getDeliveryQuantity()));
        inventoryService.createAcknowledgementReceipt(inventoryRequest);
    }

    public void sellRegCodeToExistingUser(SellUserRegCodeRequest request) {
        ADC adc = adcRepository.findByLinkedAccount_Username(request.getAdc());
        List<RegistrationCode> registrationCodes = adc.getRegistrationCodes().stream()
                .filter(code -> Objects.isNull(code.getOwner())).collect(Collectors.toList());
        registrationCodes = registrationCodes.subList(0, request.getQuantity());

        User owner = userRepository.findByUsername(request.getUsername());
        registrationCodes.forEach(code -> code.setOwner(owner));

        codeRepository.saveAll(registrationCodes);
    }

    public void checkRegCode(String regCode) throws RegCodeNotFoundException, RegCodeUsedException {
        RegistrationCode code = codeRepository.findByCode(regCode);

        if(Objects.isNull(code)) {
            throw new RegCodeNotFoundException("Registration Code Not Found");
        } else if (code.getIsUsed()) {
            throw new RegCodeUsedException("Registration Code is already used");
        }
    }
}
