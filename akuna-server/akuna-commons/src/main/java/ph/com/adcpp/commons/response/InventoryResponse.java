package ph.com.adcpp.commons.response;

import lombok.Getter;
import lombok.Setter;
import ph.com.adcpp.commons.constant.InventoryType;

/**
 * @author Choy
 * @date 2/23/2020.
 */

@Getter
@Setter
public class InventoryResponse {

    private Long id;
    private Integer quantity;
    private InventoryType inventoryType;
}
