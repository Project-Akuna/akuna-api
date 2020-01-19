package ph.com.adcpp.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;
import ph.com.adcpp.commons.constant.MaritalStatus;
import ph.com.adcpp.commons.constant.Relationship;
import ph.com.adcpp.commons.response.UserResponse;

import javax.persistence.*;
import javax.validation.constraints.Size;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String firstName;

    private String lastName;

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

    private Date birthday;

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

    private Date dateRegistered;

    @OneToMany
    @JoinTable(name = "DOWNLINES", joinColumns = @JoinColumn(name = "UPLINE_ID"),
    inverseJoinColumns = @JoinColumn(name = "DOWNLINE_ID"))
    @Size(max = 3)
    private List<User> downlines;

    @Column(nullable = false)
    private Integer treeLevel;

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
        this.dateRegistered = new Date();
    }
}