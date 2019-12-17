package ph.com.adcpp.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ph.com.adcpp.models.entity.RegistrationCode;

/**
 * @author xx
 * @date 12/17/2019.
 */
@Repository
public interface RegistrationCodeRepository extends JpaRepository<RegistrationCode, String> {
}
