package ph.com.adcpp.models.service;

import org.springframework.stereotype.Service;
import ph.com.adcpp.commons.request.UpdateInventoryRequest;
import ph.com.adcpp.models.entity.Inventory;
import ph.com.adcpp.models.entity.User;
import ph.com.adcpp.models.repository.InventoryRepository;

@Service
public class InventoryService {

    private InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Inventory findByOwner(User user) {
        return inventoryRepository.findByOwnerUser(user);
    }

    public void save(Inventory inventory) {
        inventoryRepository.save(inventory);
    }

    public void updateInventory(UpdateInventoryRequest request) {

    }
}
