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
@Table(name = "TBL_ADC")
public class ADC {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADC_ID")
    private Long id;

    @Column(name = "ADC_NAME", unique = true, nullable = false)
    private String name;

    @Column(name = "ADC_CODE", unique = true, nullable = false)
    private String code;

    @Column(name = "ADC_OFFICE_ADDRESS", nullable = false)
    private String officeAddress;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ADC_CITY")
    private City city;

    @Column(name = "ADC_PHONE_1", nullable = false)
    private String landLineNumber1;

    @Column(name = "ADC_PHONE_2")
    private String landLineNumber2;

    @Column(name = "ADC_MOBILE_1", nullable = false)
    private String mobileNumber1;

    @Column(name = "ADC_MOBILE_2")
    private String mobileNumber2;

    @Column(name = "ADC_EMAIL", nullable = false)
    private String email;

    private Boolean isSelling;


}
