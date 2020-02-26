package ph.com.adcpp.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Choy
 * @date 12/17/2019.
 */
@Getter
@Setter
@Entity
public class RegistrationCode {

    @Id
    private String code;

    private BigDecimal amount;
    private Boolean isUsed;

    @OneToOne
    @JoinColumn(name = "SOLD_TO")
    @JsonManagedReference
    private User soldTo;

    @ManyToOne
    @JoinColumn(name = "ADDED_BY")
    private User addedBy;

    @ManyToOne
    @JoinColumn(name = "OWNER_USER")
    @JsonBackReference
    private User owner;

    @ManyToOne
    @JoinColumn(name = "OWNER_DEPOT")
    @JsonIgnore
    private Depot ownerDepot;

    @ManyToOne
    @JoinColumn(name = "OWNER_ADC")
    @JsonIgnore
    private ADC ownerAdc;

    private LocalDateTime dtimeCreated;

    private LocalDateTime dtimeUsed;

    public RegistrationCode(String code) {
        this.code = code;
    }

    public RegistrationCode() {
    }

    @PrePersist
    protected void onCreate() {
        this.dtimeCreated = LocalDateTime.now();
    }
}
