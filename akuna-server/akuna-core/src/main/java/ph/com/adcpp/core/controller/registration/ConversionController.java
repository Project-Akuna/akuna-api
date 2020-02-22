package ph.com.adcpp.core.controller.registration;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ph.com.adcpp.commons.request.ConversionRequest;
import ph.com.adcpp.commons.response.BaseResponse;
import ph.com.adcpp.utils.ResponseUtil;

@RestController
@RequestMapping("/api/conversion")
public class ConversionController {

    @PostMapping("/convert-wallet-to-bottles")
    public BaseResponse convertWalletToBottles(@RequestBody ConversionRequest request) {

        return ResponseUtil.success();
    }
}
