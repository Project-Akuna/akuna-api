package ph.com.adcpp.models.entity;

import lombok.Getter;
import lombok.Setter;
import ph.com.adcpp.commons.constant.MaritalStatus;
import ph.com.adcpp.commons.constant.Relationship;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * @author raymond.galima
 * @date 12/3/2019.
 */

@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToOne
    @JoinColumn(name = "ADC_ID")
    private ADC adc;

    @OneToOne
    @JoinColumn(name = "IMMEDIATE_UPLINE_ID")
    private User upline;

    @OneToOne
    @JoinColumn(name = "DIRECT_SPONSOR_ID")
    private User directSponsor;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID"),
    inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Role> roles;

    private Date birthday;

    private String address;

    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    private String spouse;

    private String successor;

    @Enumerated(EnumType.STRING)
    private Relationship relationship;

    private Date dateRegistered;

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
