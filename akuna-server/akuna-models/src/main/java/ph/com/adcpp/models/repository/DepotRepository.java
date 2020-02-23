package ph.com.adcpp.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ph.com.adcpp.models.entity.Depot;

@Repository
public interface DepotRepository extends JpaRepository<Depot, Long> {
    Depot findByName(String name);
}
