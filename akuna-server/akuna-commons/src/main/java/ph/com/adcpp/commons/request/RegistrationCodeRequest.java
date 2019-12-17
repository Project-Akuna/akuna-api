package ph.com.adcpp.commons.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Choy
 * @date 12/17/2019.
 */

@Getter
@Setter
public class RegistrationCodeRequest {

    private Integer quantity;
    private Long adcId;
    private Long addedBy;
    private Long soldTo;
    private BigDecimal amount;
}
