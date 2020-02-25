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
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "REG_CODE_PRICE")
    private BigDecimal codePrice;

    @Column(name = "BOT_PRICE")
    private BigDecimal price;

    @Column(name = "ADC_FEE")
    private BigDecimal adcFee;

    @Column(name = "DRI_FEE")
    private BigDecimal driFee;

    @Column(name = "DATE")
    private LocalDateTime dtimeCreated;

    private String flavor;

    @Column(name = "PRODUCT_NAME")
    private String name;

    @PrePersist
    protected void onCreate() {
        dtimeCreated = LocalDateTime.now();
        flavor = flavor.toUpperCase();
    }
}
