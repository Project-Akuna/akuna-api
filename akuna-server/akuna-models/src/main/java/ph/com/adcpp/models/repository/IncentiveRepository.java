package ph.com.adcpp.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ph.com.adcpp.commons.constant.IncentiveStatus;
import ph.com.adcpp.commons.constant.IncentiveType;
import ph.com.adcpp.models.entity.Incentive;

import java.time.LocalDateTime;

/**
 * @author Choy
 * @date 1/29/2020.
 */

@Repository
public interface IncentiveRepository extends JpaRepository<Incentive, Long> {

    Incentive findByDtimeCreatedBetweenAndIncentiveType(LocalDateTime startDate, LocalDateTime endDate,
                                                        IncentiveType type);
    Incentive findByIncentiveTypeAndIncentiveStatus(IncentiveType type, IncentiveStatus status);
}
