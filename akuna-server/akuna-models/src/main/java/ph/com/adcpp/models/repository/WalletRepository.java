package ph.com.adcpp.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ph.com.adcpp.models.entity.Wallet;

/**
 * @author Choy
 * @date 1/31/2020.
 */

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
