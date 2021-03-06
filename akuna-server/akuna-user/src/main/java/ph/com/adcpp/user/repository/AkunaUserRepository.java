package ph.com.adcpp.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ph.com.adcpp.user.model.AkunaUser;

/**
 * @Author raymond.galima
 * @date 12/3/2019.
 */
@Repository
public interface AkunaUserRepository extends JpaRepository<AkunaUser, Long> {

    AkunaUser findByUsername(String username);
}
