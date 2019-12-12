package ph.com.adcpp.commons.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Choy
 * @date 12/12/2019.
 */
@Getter
@Setter
public class ProductRequest {

    private Long id;
    private BigDecimal codePrice;
    private BigDecimal price;
    private BigDecimal adcFee;
    private BigDecimal driFee;
    private Date dtimeCreated;
}
