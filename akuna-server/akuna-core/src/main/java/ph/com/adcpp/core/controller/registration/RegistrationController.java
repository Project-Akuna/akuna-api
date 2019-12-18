package ph.com.adcpp.core.controller.registration;

import org.springframework.web.bind.annotation.*;
import ph.com.adcpp.commons.request.EmailRequest;
import ph.com.adcpp.commons.request.RegistrationCodeRequest;
import ph.com.adcpp.commons.response.BaseResponse;
import ph.com.adcpp.email.EmailService;
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

    public RegistrationController(RegistrationCodeService codeService,
                                  EmailService emailService) {
        this.codeService = codeService;
        this.emailService = emailService;
    }

    @PostMapping("/generate-codes")
    public BaseResponse generateCode(@RequestBody RegistrationCodeRequest request) {
        return ResponseUtil.success(codeService.generateCodes(request));
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
