package ph.com.adcpp.core.controller.adc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ph.com.adcpp.commons.request.ADCRequest;
import ph.com.adcpp.commons.response.BaseResponse;
import ph.com.adcpp.models.service.ADCService;
import ph.com.adcpp.utils.ResponseUtil;

import javax.validation.Valid;

/**
 * @author Choy
 * @date 12/19/2019.
 */

@Slf4j
@RestController
@RequestMapping("/api/adc")
public class ADCController {

    private ADCService adcService;

    public ADCController(ADCService adcService) {
        this.adcService = adcService;
    }

    @PostMapping("/save")
    public BaseResponse saveADC(@RequestBody @Valid ADCRequest request) {
        try {
            return ResponseUtil.success(adcService.save(request));
        } catch (Exception ex) {
            log.error("Error saving adc [{}]", ex.getMessage());
            return ResponseUtil.error(ex);
        }
    }

    @GetMapping("/get-all-adcs")
    public BaseResponse getAllADCs() {
        return ResponseUtil.success(adcService.getAllADCs());
    }
}
