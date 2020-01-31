package ph.com.adcpp.models.entity;

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

    @ManyToOne
    @JoinColumn(name = "SOLD_TO")
    private User soldTo;

    @ManyToOne
    @JoinColumn(name = "ADDED_BY")
    private User addedBy;

    private LocalDateTime dtimeCreated;

    private LocalDateTime dtimeUsed;

    @PrePersist
    protected void onCreate() {
        this.dtimeCreated = LocalDateTime.now();
    }
}
