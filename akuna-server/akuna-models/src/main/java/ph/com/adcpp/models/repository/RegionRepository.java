package ph.com.adcpp.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ph.com.adcpp.models.entity.Region;

/**
 * @author Choy
 * @date 12/17/2019.
 */
@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
}
