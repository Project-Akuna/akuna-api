package ph.com.adcpp.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Choy
 * @date 12/12/2019.
 */

@Getter
@Setter
@Entity
public class ADC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADC_ID")
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private String officeAddress;

    @OneToOne(fetch = FetchType.EAGER)
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

    public ADC(Long id) {
        this.id = id;
    }

    public ADC() {
    }
}
