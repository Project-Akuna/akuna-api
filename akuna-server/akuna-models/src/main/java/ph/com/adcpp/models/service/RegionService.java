package ph.com.adcpp.models.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ph.com.adcpp.commons.response.RegionResponse;
import ph.com.adcpp.models.repository.RegionRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Choy
 * @date 12/17/2019.
 */

@Slf4j
@Service
public class RegionService {

    private RegionRepository regionRepository;
    private ObjectMapper mapper;

    public RegionService(RegionRepository regionRepository, ObjectMapper mapper) {
        this.regionRepository = regionRepository;
        this.mapper = mapper;
    }

    public List<RegionResponse> findAllRegions() {
        log.info("Getting all regions...");

        List<RegionResponse> responseList = new ArrayList<>();
        regionRepository.findAll().forEach(region -> responseList.add(mapper.convertValue(region, RegionResponse.class)));

        log.info("Returned [{}] regions.", responseList.size());
        return responseList;
    }
}
