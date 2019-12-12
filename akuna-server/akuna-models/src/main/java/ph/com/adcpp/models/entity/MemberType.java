package ph.com.adcpp.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Choy
 * @date 12/12/2019.
 */
@Getter
@Setter
@Entity
@Table(name = "TBL_MEMBER_TYPE")
public class MemberType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEM_TYPE_ID")
    private Long id;

    @Column(name = "MEM_TYPE_NAME")
    private String name;

    @Column(name = "MEM_TYPE_CODE")
    private String code;

    @Column(name = "MEM_TYPE_PRICE")
    private BigDecimal price;

    @Column(name = "MEM_TYPE_DISCOUNT")
    private BigDecimal discount;

    @Column(name = "MEM_TYPE_AKUNA_SAVINGS")
    private BigDecimal akunaSavings;

    @Column(name = "MEM_TYPE_STATUS")
    private Integer status = 1;

    @Column(name = "MEM_TYPE_DATE_ADDED")
    private Date dateAdded;

    @PrePersist
    protected void onCreate() {
        dateAdded = new Date();
    }
}
