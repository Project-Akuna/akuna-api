package ph.com.adcpp.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer stock;

    private BigDecimal price;

    private LocalDateTime dtimeCreated;
    private LocalDateTime dtimeUpdated;

    @OneToOne
    @JoinColumn(name = "OWNER_ID")
    private User owner;

    public Inventory(User user) {
        this.owner = user;
    }
}
