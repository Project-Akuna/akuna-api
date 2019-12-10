package ph.com.adcpp.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ph.com.adcpp.user.model.Role;

/**
 * @Author raymond.galima
 * @date 12/7/2019.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
