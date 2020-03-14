package ph.com.adcpp.commons.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Choy
 * @date 3/14/2020.
 */

@Getter
@Setter
public class WalletResponse {

    private BigDecimal amount;
}