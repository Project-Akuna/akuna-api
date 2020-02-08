package ph.com.adcpp.commons.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Choy
 * @date 12/20/2019.
 */

@Getter
@Setter
public class UserResponse {
    private Long id;
    private String username;
    private String fullName;
}
