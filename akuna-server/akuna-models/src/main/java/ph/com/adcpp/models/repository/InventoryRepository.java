package ph.com.adcpp.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ph.com.adcpp.models.entity.ADC;
import ph.com.adcpp.models.entity.Depot;
import ph.com.adcpp.models.entity.Inventory;
import ph.com.adcpp.models.entity.User;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    List<Inventory> findByOwnerUser_Username(String username);
    List<Inventory> findByOwnerDepot_Name(String name);
    List<Inventory> findByOwnerAdc_Id(String id);
}
