package ph.com.adcpp.models.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import ph.com.adcpp.commons.constant.RoleConstant;

import javax.persistence.*;

/**
 * @author raymond.galima
 * @date 12/7/2019.
 */
@Getter
@Setter
@Entity
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID")
    private Long id;

    @Column(unique = true)
    private String name;

    public Role(RoleConstant roleConstant) {
        this.id = roleConstant.getId();
        this.name = roleConstant.getName();
    }

    public Role(Long id) {
        this.id = id;
    }

    public Role() {
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
