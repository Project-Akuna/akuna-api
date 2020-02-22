package ph.com.adcpp.core.controller.depot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ph.com.adcpp.commons.request.DepotRequest;
import ph.com.adcpp.commons.response.BaseResponse;
import ph.com.adcpp.models.service.DepotService;
import ph.com.adcpp.utils.ResponseUtil;

@Slf4j
@RestController
@RequestMapping("/api/depot")
public class DepotController {

    private DepotService depotService;

    public DepotController(DepotService depotService) {
        this.depotService = depotService;
    }

    @GetMapping("/get-all-depot")
    public BaseResponse getAllDepot() {
        return ResponseUtil.success(depotService.getAllDepot());
    }

    @PostMapping("/save")
    public BaseResponse saveDepot(@RequestBody DepotRequest request) {
        depotService.save(request);
        return ResponseUtil.success();
    }
}