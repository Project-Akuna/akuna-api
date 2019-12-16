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
@Table(name = "TBL_CITY")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PSGCCODE")
    private String psgcCode;

    @Column(name = "PROVCODE")
    private String provCode;

    @Column(name = "CITYMUNCODE")
    private String cityCode;

    @Column(name = "CITYMUNDESC")
    private String name;

    @Column(name = "ZIP_CODE")
    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "REG_ID")
    private Region region;
}
