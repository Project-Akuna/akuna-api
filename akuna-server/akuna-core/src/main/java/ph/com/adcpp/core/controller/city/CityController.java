package ph.com.adcpp.core.controller.city;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ph.com.adcpp.commons.response.BaseResponse;
import ph.com.adcpp.models.service.CityService;
import ph.com.adcpp.utils.ResponseUtil;

/**
 * @author Choy
 * @date 12/17/2019.
 */

@RestController
@RequestMapping("/api/city")
public class CityController {

    private CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/get-all-city/{regionId}")
    public BaseResponse getAllCityByRegion(@PathVariable("regionId") Long regionId) {
        return ResponseUtil.success(cityService.findAllCities(regionId));
    }
}
