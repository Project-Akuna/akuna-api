package ph.com.adcpp.models.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ph.com.adcpp.commons.constant.IncentiveType;
import ph.com.adcpp.models.entity.Incentive;
import ph.com.adcpp.models.repository.IncentiveRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author Choy
 * @date 1/29/2020.
 */

@Slf4j
@Service
public class IncentiveService {

    private IncentiveRepository incentiveRepository;

    public IncentiveService(IncentiveRepository incentiveRepository) {
        this.incentiveRepository = incentiveRepository;
    }

    public Incentive save(Incentive incentive) {
        return incentiveRepository.save(incentive);
    }

    public Incentive getIncentiveByCurrentDateAndType(IncentiveType type) {
        return incentiveRepository.findByDtimeCreatedBetweenAndIncentiveType(LocalDateTime.now().with(LocalTime.MIN),
                LocalDateTime.now().with(LocalTime.MAX), type);
    }
}
