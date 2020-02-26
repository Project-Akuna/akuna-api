package ph.com.adcpp.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Depot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPOT_ID")
    private Long id;

    @Column(unique = true, nullable = false, name = "DEPOT_NAME")
    private String name;

    @Column(unique = true, nullable = false, name = "DEPOT_CODE")
    private String code;

    @Column(nullable = false)
    private String officeAddress;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CITY_ID")
    private City city;

    @Column(name = "PHONE_1", nullable = false)
    private String landLineNumber1;

    @Column(name = "PHONE_2")
    private String landLineNumber2;

    @Column(name = "MOBILE_1", nullable = false)
    private String mobileNumber1;

    @Column(name = "MOBILE_2")
    private String mobileNumber2;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(nullable = false)
    private Boolean isSelling;

    @ManyToOne
    @JoinColumn(name = "OWNER_ID")
    private User owner;

    @OneToOne(mappedBy = "ownerDepot")
    @JsonIgnore
    private Inventory inventory;

    @OneToOne(mappedBy = "ownerDepot", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Wallet wallet;

    @OneToMany(mappedBy = "ownerDepot")
    @JsonIgnore
    private List<RegistrationCode> registrationCodes = new ArrayList<>();

    public Depot(Long id) {
        this.id = id;
    }

    public Depot() {
    }
}
