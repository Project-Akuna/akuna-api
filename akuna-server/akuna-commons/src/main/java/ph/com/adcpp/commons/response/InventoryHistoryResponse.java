package ph.com.adcpp.commons.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Choy
 * @date 2/23/2020.
 */

@Getter
@Setter
public class InventoryHistoryResponse {

    private Long id;
    private LocalDateTime deliveryDate;
    private String deliveryCode;
    private Integer deliveryQuantity;
    private BigDecimal deliveryPrice;
    private Integer quantitySold;
    private BigDecimal sellingPrice;
    private Integer beginningQuantity;
    private Integer endingQuantity;
}
