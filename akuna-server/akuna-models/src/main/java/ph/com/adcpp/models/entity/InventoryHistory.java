package ph.com.adcpp.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class InventoryHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INV_HISTORY_ID")
    private Long id;

    private LocalDateTime deliveryDate;
    private String deliveryCode;
    private Integer deliveryQuantity;
    private BigDecimal deliveryPrice;
    private String soldTo;
    private Integer quantitySold;
    private BigDecimal sellingPrice;
    private Integer beginningQuantity;
    private Integer endingQuantity;

    @OneToOne
    @JoinColumn(name = "PRODUCT_ID")
    @JsonBackReference
    private Product product;

    @ManyToOne
    @JoinColumn(name = "INVENTORY_ID")
    @JsonBackReference
    private Inventory inventory;
}
