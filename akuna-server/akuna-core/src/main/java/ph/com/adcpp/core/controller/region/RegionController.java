package ph.com.adcpp.core.controller.region;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ph.com.adcpp.commons.response.BaseResponse;
import ph.com.adcpp.models.service.RegionService;
import ph.com.adcpp.utils.ResponseUtil;

/**
 * @author Choy
 * @date 12/17/2019.
 */

@Slf4j
@RestController
@RequestMapping("/api/region")
public class RegionController {

    private RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping("/get-all-regions")
    public BaseResponse getAllRegions() {
        return ResponseUtil.success(regionService.findAllRegions());
    }
}
