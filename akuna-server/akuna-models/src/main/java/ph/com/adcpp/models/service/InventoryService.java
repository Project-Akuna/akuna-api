package ph.com.adcpp.models.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import ph.com.adcpp.commons.constant.InventoryType;
import ph.com.adcpp.commons.request.UpdateInventoryRequest;

import ph.com.adcpp.commons.response.InventoryResponse;
import ph.com.adcpp.models.entity.*;
import ph.com.adcpp.models.repository.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class InventoryService {

    private InventoryRepository inventoryRepository;
    private ObjectMapper mapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DepotRepository depotRepository;

    @Autowired
    private ADCRepository adcRepository;

    @Autowired
    private AcknowledgementRepo acknowledgementRepo;


    public InventoryService(InventoryRepository inventoryRepository, ObjectMapper mapper) {
        this.inventoryRepository = inventoryRepository;
        this.mapper = mapper;
    }

    public List<Inventory> findByOwner(String username) {
        return inventoryRepository.findByOwnerUser_Username(username);
    }

    public void save(Inventory inventory) {
        inventoryRepository.save(inventory);
    }

    public void updateInventorySysAdmin(UpdateInventoryRequest request) {
        log.info("Updating inventory of SysAdmin...");
        inventoryRepository.findByOwnerUser_Username(request.getSoldTo())
                .stream().filter(inventory -> inventory.getInventoryType() == request.getInventoryType())
                .forEach(inventory -> {
                    update(request, inventory, inventory.getQuantity() + request.getDeliveryQuantity());
                    createAcknowledgementReceipt(request);
                });
        log.info("Inventory updated.");
    }

    public void updateInventoryOfDepot(UpdateInventoryRequest request) {
        log.info("Updating inventory of Depot: {}", request.getSoldTo());
        inventoryRepository.findByOwnerDepot_Name(request.getSoldTo())
                .stream().filter(inventory -> inventory.getInventoryType() == request.getInventoryType())
                .forEach(inventory -> {
                    inventoryRepository.findByOwnerUser_Username("asd")
                            .stream().filter(inventory1 -> inventory1.getInventoryType() == request.getInventoryType())
                            .forEach(inventory1 -> {
                                update(request, inventory1, inventory1.getQuantity() - request.getQuantitySold());
                            });

                    inventory.setQuantity(inventory.getQuantity() + request.getQuantitySold());
                    inventoryRepository.save(inventory);
                    createAcknowledgementReceipt(request);
                });
        log.info("Inventory updated.");
    }

    public void initializeInventory() {
        Arrays.stream(InventoryType.values()).forEach(inventoryType -> {
            Inventory inventory = new Inventory();
            inventory.setInventoryType(inventoryType);
            inventory.setOwnerUser(new User(1L));
            inventory.setPrice(new BigDecimal(750));
            inventory.setQuantity(0);
            inventoryRepository.save(inventory);
        });
    }

    public List<InventoryResponse> getInventoryOfUser(String username) {
        return findByOwner(username)
                .stream().map(inventory -> mapper.convertValue(inventory, InventoryResponse.class))
                .collect(Collectors.toList());
    }

    private void update(UpdateInventoryRequest request, Inventory inventory, Integer endingQuantity) {

        InventoryHistory history = mapper.convertValue(request, InventoryHistory.class);
        history.setBeginningQuantity(inventory.getQuantity());
        history.setEndingQuantity(endingQuantity);
        history.setProduct(mapper.convertValue(request.getProduct(), Product.class));
        history.setInventory(inventory);

        inventory.setQuantity(endingQuantity);
        inventory.getHistory().add(history);

        inventoryRepository.save(inventory);
    }

    private void createAcknowledgementReceipt(UpdateInventoryRequest request) {
        AcknowledgementReceipt receipt = new AcknowledgementReceipt();
        receipt.setQuantity((Objects.nonNull(request.getDeliveryQuantity()) ? request.getDeliveryQuantity() : request.getQuantitySold()));
        receipt.setSoldTo(request.getSoldTo());
        receipt.setSoldBy((Objects.nonNull(request.getSoldBy()) ? request.getSoldBy() : null));
        BigDecimal amount = (Objects.nonNull(request.getDeliveryPrice())) ? request.getDeliveryPrice() : request.getSellingPrice();
        receipt.setAmount(amount);
        setReceiptDetails(receipt);

        acknowledgementRepo.save(receipt);
    }

    private void setReceiptDetails(AcknowledgementReceipt receipt) {
        User user = userRepository.findByUsername(receipt.getSoldTo());
        Depot depot = depotRepository.findByName(receipt.getSoldTo());
        ADC adc = adcRepository.findByName(receipt.getSoldTo());
        if (Objects.nonNull(user)) {
            receipt.setEmail(user.getEmail());
            receipt.setFirstName(user.getFirstName());
            receipt.setMiddleName(user.getMiddleName());
            receipt.setLastName(user.getLastName());
        } else if (Objects.nonNull(depot)) {
            receipt.setEmail(depot.getEmail());
            receipt.setFirstName(depot.getName());
        } else {
            receipt.setEmail(adc.getEmail());
            receipt.setFirstName(adc.getName());
        }
    }
}
