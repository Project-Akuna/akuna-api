package ph.com.adcpp.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ph.com.adcpp.models.entity.City;
import ph.com.adcpp.models.entity.Region;

import java.util.List;

/**
 * @author Choy
 * @date 12/17/2019.
 */

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findByRegion(Region region);
}
