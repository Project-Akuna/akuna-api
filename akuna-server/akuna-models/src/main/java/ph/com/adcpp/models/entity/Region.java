package ph.com.adcpp.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author Choy
 * @date 12/12/2019.
 */
@Getter
@Setter
@Entity
@Table(name = "TBL_REGION")
public class Region {

    @Id
    private Long id;

    @Column(name = "REGDESC")
    private String description;

    @Column(name = "REGCODE")
    private Long code;

    @Column(name = "PSGCCODE")
    private String psgcCode;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "region")
    @JsonIgnore
    private List<City> cities;
}
