package ph.com.adcpp.core.controller.registration;

import org.springframework.web.bind.annotation.*;
import ph.com.adcpp.commons.request.EmailRequest;
import ph.com.adcpp.commons.request.RegistrationCodeRequest;
import ph.com.adcpp.commons.request.SellDepotCodeRequest;
import ph.com.adcpp.commons.response.BaseResponse;
import ph.com.adcpp.email.EmailService;
import ph.com.adcpp.models.service.InventoryService;
import ph.com.adcpp.models.service.RegistrationCodeService;
import ph.com.adcpp.utils.ResponseUtil;

/**
 * @author Choy
 * @date 12/17/2019.
 */

@RestController
@RequestMapping("/api/registration")
public class RegistrationController {

    private RegistrationCodeService codeService;
    private EmailService emailService;
    private InventoryService inventoryService;

    public RegistrationController(RegistrationCodeService codeService, EmailService emailService, InventoryService inventoryService) {
        this.codeService = codeService;
        this.emailService = emailService;
        this.inventoryService = inventoryService;
    }

    @PostMapping("/generate-codes-depot")
    public BaseResponse generateCode(@RequestBody SellDepotCodeRequest request) {
        codeService.sellDepotRegCode(request);
        return ResponseUtil.success();
    }

    @GetMapping("/all")
    public BaseResponse all() {
        return ResponseUtil.success(codeService.findAll());
    }

    @PostMapping("/email")
    public BaseResponse email(@RequestBody EmailRequest request) {
        emailService.sendEmail(request);
        return ResponseUtil.success();
    }
}
