package ph.com.adcpp.core.controller.inventory;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ph.com.adcpp.commons.request.UpdateInventoryRequest;
import ph.com.adcpp.commons.request.UserRequest;
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

    @PostMapping("/update-sysadmin")
    public BaseResponse updateSysAdmin(@RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) UpdateInventoryRequest request) {
        inventoryService.updateInventorySysAdmin(request);
        return ResponseUtil.success();
    }

    @PostMapping("/update-depot")
    public BaseResponse updateDepot(@RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) UpdateInventoryRequest request) {
        inventoryService.updateInventoryOfDepot(request);
        return ResponseUtil.success();
    }

//    @PostMapping("/update-adc")
//    public BaseResponse updateAdc(@RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) UpdateInventoryRequest request) {
//        inventoryService.updateInventoryOfAdc(request);
//        return ResponseUtil.success();
//    }

    @PostMapping("/initialize-inventory")
    public BaseResponse initializeInventory() {
        inventoryService.initializeInventory();
        return ResponseUtil.success();
    }

    @GetMapping("/get-user-inventory/{username}")
    public BaseResponse saveUserInventory(@PathVariable String username) {
        return ResponseUtil.success(inventoryService.getInventoryOfUser(username));
    }
}
