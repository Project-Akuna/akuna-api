package ph.com.adcpp.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ph.com.adcpp.models.entity.ADC;
import ph.com.adcpp.models.entity.User;

import java.util.List;


/**
 * @author raymond.galima
 * @date 12/3/2019.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    List<User> findByAdcAndTreeLevel(ADC adc, Integer treeLevel);
}
