package ph.com.adcpp.commons.response;

import lombok.Getter;
import lombok.Setter;
import ph.com.adcpp.commons.request.ProductRequest;

import java.math.BigDecimal;

/**
 * @author Choy
 * @date 12/12/2019.
 */

@Getter
@Setter
public class ProductResponse extends ProductRequest {

    private Long id;
    private String name;
    private String flavor;
    private BigDecimal price;
}
