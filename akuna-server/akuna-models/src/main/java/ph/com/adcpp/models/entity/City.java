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
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String psgcCode;

    private String provCode;

    private String cityCode;

    private String name;

    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "REG_ID")
    private Region region;
}
