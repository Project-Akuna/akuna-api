package ph.com.adcpp.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ph.com.adcpp.models.entity.Member;

/**
 * @author Choy
 * @date 12/12/2019.
 */

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
}
