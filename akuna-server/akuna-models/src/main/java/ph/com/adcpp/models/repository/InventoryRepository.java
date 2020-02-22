package ph.com.adcpp.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ph.com.adcpp.models.entity.ADC;
import ph.com.adcpp.models.entity.Depot;
import ph.com.adcpp.models.entity.Inventory;
import ph.com.adcpp.models.entity.User;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Inventory findByOwnerUser(User user);
    Inventory findByOwnerDepot(Depot depot);
    Inventory findByOwnerAdc(ADC adc);
}
