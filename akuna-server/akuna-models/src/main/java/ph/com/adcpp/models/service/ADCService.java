package ph.com.adcpp.models.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ph.com.adcpp.models.entity.ADC;
import ph.com.adcpp.models.repository.ADCRepository;

/**
 * @author Choy
 * @date 12/12/2019.
 */

@Slf4j
@Service
public class ADCService {

    private ADCRepository adcRepository;

    public ADCService(ADCRepository adcRepository) {
        this.adcRepository = adcRepository;
    }

    public ADC save(ADC adc) {
        return adcRepository.save(adc);
    }
}
