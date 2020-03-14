package ph.com.adcpp.core.controller.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ph.com.adcpp.commons.request.UpdateInventoryRequest;
import ph.com.adcpp.commons.request.UserRequest;
import ph.com.adcpp.commons.response.BaseResponse;
import ph.com.adcpp.models.service.InventoryHistoryService;
import ph.com.adcpp.models.service.InventoryService;
import ph.com.adcpp.utils.ResponseUtil;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private InventoryService inventoryService;
    @Autowired
    private InventoryHistoryService inventoryHistoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/update-sysadmin")
    public BaseResponse updateSysAdmin(@RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) UpdateInventoryRequest request) {
        inventoryService.updateInventorySysAdmin(request);
        return ResponseUtil.success();
    }

    @PostMapping("/initialize-inventory")
    public BaseResponse initializeInventory() {
        inventoryService.initializeInventory();
        return ResponseUtil.success();
    }

    @GetMapping("/get-user-inventory/{username}")
    public BaseResponse saveUserInventory(@PathVariable String username) {
        return ResponseUtil.success(inventoryService.getInventoryOfUser(username));
    }

    @GetMapping("/get-history/{username}")
    public BaseResponse getHistory(@PathVariable String username) {
        return ResponseUtil.success(inventoryService.findHistory(username));
    }
}
