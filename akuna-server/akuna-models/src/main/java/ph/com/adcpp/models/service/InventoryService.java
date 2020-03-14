package ph.com.adcpp.models.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ph.com.adcpp.commons.constant.InventoryType;
import ph.com.adcpp.commons.request.ProductRequest;
import ph.com.adcpp.commons.request.UpdateInventoryRequest;

import ph.com.adcpp.commons.response.InventoryHistoryResponse;
import ph.com.adcpp.commons.response.InventoryResponse;
import ph.com.adcpp.models.entity.*;
import ph.com.adcpp.models.repository.*;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;

@Slf4j
@Service
@Transactional
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

    @Autowired
    private InventoryHistoryRepository inventoryHistoryRepository;


    public InventoryService(InventoryRepository inventoryRepository, ObjectMapper mapper) {
        this.inventoryRepository = inventoryRepository;
        this.mapper = mapper;
    }

    public void save(Inventory inventory) {
        inventoryRepository.save(inventory);
    }

    public void updateInventorySysAdmin(UpdateInventoryRequest request) {
        log.info("Updating inventory of SysAdmin...");

        log.info("Adding delivery quantity: {}", request.getDeliveryQuantity());
        Inventory inventory = inventoryRepository.findByOwnerUser_Username("asd");
        Integer beginningQty = inventory.getQuantity();

        createHistory(request, inventory, beginningQty, beginningQty + request.getDeliveryQuantity(), request.getProduct());
        createAcknowledgementReceipt(request);

        log.info("Inventory updated.");
    }

    public void updateInventoryDepot(UpdateInventoryRequest request) {
        log.info("Updating inventory of depot[{}]...", request.getSoldBy());

        Inventory inventory = inventoryRepository.findByOwnerDepot_Id(request.getDepotId());

        Integer beginningQty = inventory.getQuantity();
        createHistory(request, inventory, beginningQty, beginningQty + request.getDeliveryQuantity(), request.getProduct());
        createAcknowledgementReceipt(request);

        log.info("Inventory updated.");
    }

    public void initializeInventory() {
        Arrays.stream(InventoryType.values()).forEach(inventoryType -> {
            Inventory inventory = new Inventory();
            inventory.setInventoryType(inventoryType);
            inventory.setOwnerUser(new User(2L));
            inventory.setPrice(new BigDecimal(750));
            inventory.setQuantity(0);
            inventoryRepository.save(inventory);
        });
    }

    public InventoryResponse getInventoryOfUser(String username) {
        return mapper.convertValue(inventoryRepository.findByOwnerUser_Username(username), InventoryResponse.class);
    }

    public void createHistory(UpdateInventoryRequest request, Inventory inventory, Integer beginningQty, Integer endingQuantity, ProductRequest productRequest) {

        InventoryHistory history = new InventoryHistory();
        history.setBeginningQuantity(beginningQty);
        history.setEndingQuantity(endingQuantity);
        history.setProduct(mapper.convertValue(productRequest, Product.class));
        history.setDeliveryCode(request.getDeliveryCode());
        history.setInventory(inventory);
        history.setQuantitySold(request.getQuantitySold());
        history.setSellingPrice(request.getSellingPrice());
        history.setSoldTo(request.getSoldTo());
        history.setDeliveryQuantity((request.getDeliveryQuantity() < 0) ? null : request.getDeliveryQuantity());
        history = inventoryHistoryRepository.save(history);

        inventory.setQuantity(endingQuantity);
        inventory.getHistory().add(history);

        inventoryRepository.save(inventory);
    }

    public void createAcknowledgementReceipt(UpdateInventoryRequest request) {
        log.info("Creating acknowledgement receipt...");
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

    public List<InventoryHistoryResponse> findHistory(String username) {
        log.info("Getting histories for user [{}]", username);

        User user = userRepository.findByUsername(username);
        List<InventoryHistoryResponse> histories = new ArrayList<>();
        user.getInventory().getHistory().forEach(history -> histories.add(mapper.convertValue(history, InventoryHistoryResponse.class)));

        log.info("Retrieved [{}] histories for user [{}]", histories.size(), username);
        return histories;
    }
}
