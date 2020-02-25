package ph.com.adcpp.core.controller.cashier;

import org.springframework.web.bind.annotation.*;
import ph.com.adcpp.commons.request.UserRequest;
import ph.com.adcpp.commons.response.BaseResponse;
import ph.com.adcpp.models.service.CashierService;
import ph.com.adcpp.utils.ResponseUtil;

@RestController
@RequestMapping("/api/cashier")
public class CashierController {

    private CashierService cashierService;

    public CashierController(CashierService cashierService) {
        this.cashierService = cashierService;
    }

    @GetMapping("/get-all-cashier")
    public BaseResponse getAllCashier() {
        return ResponseUtil.success(cashierService.getCashiers());
    }

    @PostMapping("/save")
    public BaseResponse save(@RequestBody UserRequest request) {
        cashierService.save(request);
        return ResponseUtil.success();
    }
}
