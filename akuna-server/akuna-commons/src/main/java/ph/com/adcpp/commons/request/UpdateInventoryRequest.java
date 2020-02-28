package ph.com.adcpp.commons.request;

import lombok.Getter;
import lombok.Setter;
import ph.com.adcpp.commons.constant.InventoryType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private List<ProductRequest> product = new ArrayList<>();
    private String soldBy;
    private Long depotId;
}
