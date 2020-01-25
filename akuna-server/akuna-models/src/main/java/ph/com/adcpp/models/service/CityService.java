package ph.com.adcpp.models.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ph.com.adcpp.commons.response.CityResponse;
import ph.com.adcpp.models.entity.Region;
import ph.com.adcpp.models.repository.CityRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Choy
 * @date 12/17/2019.
 */

@Slf4j
@Service
public class CityService {

    private CityRepository cityRepository;
    private ObjectMapper mapper;

    public CityService(CityRepository cityRepository, ObjectMapper mapper) {
        this.cityRepository = cityRepository;
        this.mapper = mapper;
    }

    public List<CityResponse> findAllCities(Long regionId) {
        log.info("Getting cities in region [{}]", regionId);

        Region region = new Region(regionId);
        List<CityResponse> cities = new ArrayList<>();
        cityRepository.findByRegion(region).forEach(city -> cities.add(mapper.convertValue(city, CityResponse.class)));

        log.info("Found [{}] cities for region [{}]", cities.size(), regionId);
        return cities;
    }
}
