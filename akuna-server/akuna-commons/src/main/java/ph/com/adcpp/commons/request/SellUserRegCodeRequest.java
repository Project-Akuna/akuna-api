package ph.com.adcpp.commons.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Choy
 * @date 3/11/2020.
 */

@Getter
@Setter
public class SellUserRegCodeRequest {

    private Integer quantity;
    private String username;
    private String adc;
}