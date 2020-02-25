package ph.com.adcpp.commons.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Choy
 * @date 12/12/2019.
 */
@Getter
@Setter
public class ProductRequest {

    @NotNull
    private Long id;
//    @NotNull
    private BigDecimal codePrice;
//    @NotNull
    private BigDecimal price;
//    @NotNull
    private BigDecimal adcFee;
//    @NotNull
    private BigDecimal driFee;
}
