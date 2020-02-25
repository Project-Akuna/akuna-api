package ph.com.adcpp.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Choy
 * @date 1/31/2020.
 */

@Entity
@Getter
@Setter
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    @JsonBackReference
    private User user;

    @OneToOne
    @JoinColumn(name = "OWNER_DEPOT")
    @JsonBackReference
    private Depot ownerDepot;

    @OneToOne
    @JoinColumn(name = "OWNER_ADC")
    @JsonBackReference
    private ADC ownerAdc;

    private BigDecimal amount = new BigDecimal(0);

    private LocalDateTime dtimeCreated;
    private LocalDateTime dtimeUpdated;

    public Wallet(User user) {
        this.user = user;
    }

    public Wallet(Depot ownerDepot) {
        this.ownerDepot = ownerDepot;
    }

    public Wallet(ADC ownerAdc) {
        this.ownerAdc = ownerAdc;
    }

    public Wallet() {}

    @PrePersist
    protected void onCreate() {
        dtimeCreated = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        dtimeUpdated = LocalDateTime.now();
    }
}
