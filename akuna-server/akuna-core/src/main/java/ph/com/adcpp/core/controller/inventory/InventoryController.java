package ph.com.adcpp.core.controller.inventory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ph.com.adcpp.commons.request.UpdateInventoryRequest;
import ph.com.adcpp.commons.response.BaseResponse;
import ph.com.adcpp.models.service.InventoryService;
import ph.com.adcpp.utils.ResponseUtil;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/update")
    public BaseResponse update(@RequestBody UpdateInventoryRequest request) {
        inventoryService.updateInventory(request);
        return ResponseUtil.success();
    }
}
