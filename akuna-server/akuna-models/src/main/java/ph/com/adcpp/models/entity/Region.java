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
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    private Long code;

    private String psgcCode;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "region")
    @JsonIgnore
    private List<City> cities;

    public Region(Long id) {
        this.id = id;
    }

    public Region() {
    }
}
