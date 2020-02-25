package ph.com.adcpp.models.entity;

import lombok.Getter;
import lombok.Setter;
import ph.com.adcpp.commons.constant.IncentiveStatus;
import ph.com.adcpp.commons.constant.IncentiveType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private List<User> users = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private IncentiveStatus incentiveStatus;

    private LocalDateTime dtimeCreated;
    private LocalDateTime dtimeUpdated;

    public Incentive() {
    }

    public Incentive(IncentiveType incentiveType) {
        this.incentiveType = incentiveType;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    @PrePersist
    protected void onCreate() {
        incentiveStatus = IncentiveStatus.ACTIVE;
        dtimeCreated = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        dtimeUpdated = LocalDateTime.now();
    }
}
