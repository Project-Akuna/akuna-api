package ph.com.adcpp.commons.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Choy
 * @date 2/26/2020.
 */
@Getter
@Setter
public class SellDepotCodeRequest {

    private Long depotId;
    private Integer quantity;
    private Long productId;
}
