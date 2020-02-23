package ph.com.adcpp.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Choy
 * @date 2/24/2020.
 */

@Entity
@Getter
@Setter
public class AcknowledgementReceipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AR_ID")
    private Long id;

    private String soldBy;
    private String soldTo;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private BigDecimal amount;
    private Integer quantity;

    private LocalDateTime dtimeCreated;

    @PrePersist
    protected void onCreate() {
        this.dtimeCreated = LocalDateTime.now();
    }
}
