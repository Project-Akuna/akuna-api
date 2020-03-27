package ph.com.adcpp.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ph.com.adcpp.models.entity.RegistrationCode;

import java.util.List;

/**
 * @author raymond.galima
 * @date 12/17/2019.
 */
@Repository
public interface RegistrationCodeRepository extends JpaRepository<RegistrationCode, String> {

    RegistrationCode findByCode(String code);

    @Query(value = "SELECT a FROM RegistrationCode a WHERE a.ownerAdc IS NULL")
    List<RegistrationCode> getNullADC();

    @Query(value = "SELECT * FROM  Registration_Code a WHERE a.owner_depot = ?1", nativeQuery = true)
    List<RegistrationCode> getDepotCodes(Long depotId);

    @Query(value = "SELECT * FROM  Registration_Code a WHERE owner_adc = ?1", nativeQuery = true)
    List<RegistrationCode> getAdcCodes(Long adcId);
}
