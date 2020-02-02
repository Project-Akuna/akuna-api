package ph.com.adcpp.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ph.com.adcpp.models.entity.Incentive;

/**
 * @author Choy
 * @date 1/29/2020.
 */

@Repository
public interface IncentiveRepository extends JpaRepository<Incentive, Long> {
}
