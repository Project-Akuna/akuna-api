package ph.com.adcpp.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
    Page<User> findAll(Pageable page);

    @Query(value = "SELECT * FROM user u JOIN user_role ur on u.user_id = ur.user_id" +
            " WHERE ur.role_id = ?1", nativeQuery = true)
    List<User> getUsersByRoleId(Long roleId);

    List<User> findByTreeLevel(Integer treeLevel);
}
