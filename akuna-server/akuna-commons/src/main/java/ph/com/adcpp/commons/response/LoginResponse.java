package ph.com.adcpp.commons.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Choy
 * @date 1/25/2020.
 */

@Getter
@Setter
public class LoginResponse {

    private String username;
    private String sessionId;
}
