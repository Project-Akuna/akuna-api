package ph.com.adcpp.models.entity;

import lombok.Getter;
import lombok.Setter;
import ph.com.adcpp.commons.constant.IncentiveType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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
    @Column(name = "INCENTIVE_ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    private IncentiveType incentiveType;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_INCENTIVES", joinColumns = @JoinColumn(name = "INCENTIVE_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID"))
    private List<User> users;

    private LocalDateTime dtimeCreated;
    private LocalDateTime dtimeUpdated;

    public Incentive() {
    }

    public Incentive(IncentiveType incentiveType) {
        this.incentiveType = incentiveType;
    }

    @PrePersist
    protected void onCreate() {
        dtimeCreated = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        dtimeUpdated = LocalDateTime.now();
    }
}
