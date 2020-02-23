package ph.com.adcpp.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ph.com.adcpp.models.entity.AcknowledgementReceipt;

/**
 * @author Choy
 * @date 2/24/2020.
 */

@Repository
public interface AcknowledgementRepo extends JpaRepository<AcknowledgementReceipt, Long> {
}
