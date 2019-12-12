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
@Table(name = "TBL_PRODUCTS")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "REG_CODE_PRICE")
    private BigDecimal codePrice;

    @Column(name = "ALVEO_BOT_PRICE")
    private BigDecimal price;

    @Column(name = "ADC_FEE")
    private BigDecimal adcFee;

    @Column(name = "DRI_FEE")
    private BigDecimal driFee;

    @Column(name = "DATE")
    private Date dtimeCreated;

    @PrePersist
    protected void onCreate() {
        dtimeCreated = new Date();
    }
}
