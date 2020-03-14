package ph.com.adcpp.commons.request;

import lombok.Getter;
import lombok.Setter;
import ph.com.adcpp.commons.constant.InventoryType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class UpdateInventoryRequest {

    private LocalDateTime deliveryDate;
    private String deliveryCode;
    private Integer deliveryQuantity;
    private BigDecimal deliveryPrice;
    private String soldTo;
    private Integer quantitySold;
    private BigDecimal sellingPrice;
    private InventoryType inventoryType;
    private ProductRequest product;
    private String soldBy;
    private Long depotId;
}
