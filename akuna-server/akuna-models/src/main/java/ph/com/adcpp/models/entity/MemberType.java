package ph.com.adcpp.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author Choy
 * @date 12/12/2019.
 */
@Getter
@Setter
@Entity
public class MemberType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEM_TYPE_ID")
    private Long id;

    private String name;

    private String code;

    private BigDecimal price;

    private BigDecimal discount;

    private BigDecimal akunaSavings;

    private Integer status = 1;

    private LocalDateTime dateAdded;

    @PrePersist
    protected void onCreate() {
        dateAdded = LocalDateTime.now();
    }
}
