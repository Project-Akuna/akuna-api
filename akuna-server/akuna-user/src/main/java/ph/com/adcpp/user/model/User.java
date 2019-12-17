package ph.com.adcpp.user.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.Set;

/**
 * @author raymond.galima
 * @date 12/3/2019.
 */

@Getter
@Setter
@Entity
@Table(name = "TBL_USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

    private Boolean isEnabled;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "TBL_USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID"),
    inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Role> roles;

    public User() {
    }

    public User(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.isEnabled = user.getIsEnabled();
        this.roles = user.getRoles();
    }

    public User(Long id) {
        this.id = id;
    }
}
