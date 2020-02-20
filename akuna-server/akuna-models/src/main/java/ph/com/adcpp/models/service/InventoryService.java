package ph.com.adcpp.models.service;

import org.springframework.stereotype.Service;
import ph.com.adcpp.models.repository.InventoryRepository;

@Service
public class InventoryService {

    private InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    
}
