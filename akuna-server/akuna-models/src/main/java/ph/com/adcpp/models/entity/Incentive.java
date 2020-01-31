package ph.com.adcpp.models.entity;

import lombok.Getter;
import lombok.Setter;
import ph.com.adcpp.commons.constant.IncentiveType;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Choy
 * @date 1/29/2020.
 */

@Entity
@Getter
@Setter
public class Incentive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private IncentiveType incentiveType;
    private Integer count;
    private LocalDateTime dtimeCreated;
    private LocalDateTime dtimeUpdated;

    public Incentive() {
    }

    public Incentive(IncentiveType incentiveType) {
        this.incentiveType = incentiveType;
    }

    @PrePersist
    protected void onCreate() {
        count = 0; // initial count is 0 for the start of the day
        dtimeCreated = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        dtimeUpdated = LocalDateTime.now();
    }
}
