package ph.com.adcpp.models.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ph.com.adcpp.commons.request.UpdateInventoryRequest;
import ph.com.adcpp.commons.response.InventoryHistoryResponse;
import ph.com.adcpp.models.entity.InventoryHistory;
import ph.com.adcpp.models.repository.InventoryHistoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class InventoryHistoryService {

    private InventoryHistoryRepository inventoryHistoryRepository;
    private ObjectMapper mapper;

    public InventoryHistoryService(InventoryHistoryRepository inventoryHistoryRepository, ObjectMapper mapper) {
        this.inventoryHistoryRepository = inventoryHistoryRepository;
        this.mapper = mapper;
    }

    public void save(InventoryHistory inventoryHistory) {
        inventoryHistoryRepository.save(inventoryHistory);
    }

    public void save(UpdateInventoryRequest request) {
        inventoryHistoryRepository.save(mapper.convertValue(request, InventoryHistory.class));
    }

    public List<InventoryHistory> findBySoldTo(String soldTo) {
        return inventoryHistoryRepository.findBySoldTo(soldTo);
    }

    public List<InventoryHistoryResponse> findHistory(String soldTo) {
        log.info("Getting ");
        List<InventoryHistoryResponse> responses = inventoryHistoryRepository.findBySoldTo(soldTo)
                .stream().map(inventoryHistory -> mapper.convertValue(inventoryHistory, InventoryHistoryResponse.class))
                .collect(Collectors.toList());
        log.info("Retrieved [{}] histories for user [{}]", responses.size(), soldTo);
        return responses;
    }
}
