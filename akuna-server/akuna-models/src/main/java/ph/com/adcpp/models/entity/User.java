package ph.com.adcpp.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import ph.com.adcpp.commons.constant.MaritalStatus;
import ph.com.adcpp.commons.constant.Relationship;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @author raymond.galima
 * @date 12/3/2019.
 */

@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "USER_ID")
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String firstName;

    private String lastName;

    private String middleName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Boolean isEnabled;

    @OneToOne
    @JoinColumn(name = "ADC_ID")
    private ADC adc;

    @OneToOne
    @JoinColumn(name = "IMMEDIATE_UPLINE_ID")
    @JsonIgnore
    private User upline;

    @OneToOne
    @JoinColumn(name = "DIRECT_SPONSOR_ID")
    @JsonIgnore
    private User directSponsor;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "USER_ID"),
    inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Role> roles;

    private LocalDate birthday;

    private String address;

    @ManyToOne
    @JoinColumn(name = "CITY_ID", nullable = false)
    private City city;

    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    private String spouse;

    private String successor;

    @Enumerated(EnumType.STRING)
    private Relationship relationship;

    private LocalDateTime dateRegistered;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "DOWNLINES", joinColumns = @JoinColumn(name = "UPLINE_ID"),
    inverseJoinColumns = @JoinColumn(name = "DOWNLINE_ID"))
    @Size(max = 3)
    private List<User> downlines = new ArrayList<>();

    @Column(nullable = false)
    private Integer treeLevel;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    @JsonManagedReference
    private Wallet wallet;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "soldTo")
    @JsonBackReference
    private RegistrationCode registrationCode;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<RegistrationCode> ownCodes;

    @OneToOne
    @JoinColumn(name = "MEMBER_TYPE_ID")
    private MemberType memberType;

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

    public void addRole(Role role) {
        if (Objects.isNull(this.roles)) {
            this.roles = new HashSet<>();
        }
        this.roles.add(role);
    }

    @PrePersist
    protected void onCreate() {
        this.dateRegistered = LocalDateTime.now();
        this.firstName = this.firstName.toUpperCase();
        this.lastName = this.lastName.toUpperCase();
        this.middleName = this.middleName.toUpperCase();
    }
}
