package ph.com.adcpp.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @ManyToOne
    @JoinColumn(name = "ADC_ID")
    private ADC adc;

    private Boolean isUsed;

    @OneToOne
    @JoinColumn(name = "SOLD_TO")
    @JsonManagedReference
    private User soldTo;

    @ManyToOne
    @JoinColumn(name = "ADDED_BY")
    private User addedBy;

    @ManyToOne
    @JoinColumn(name = "OWNER")
    @JsonBackReference
    private User owner;

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

    @PreUpdate
    protected void onUpdate() {
        this.dtimeUsed = LocalDateTime.now();
    }
}
