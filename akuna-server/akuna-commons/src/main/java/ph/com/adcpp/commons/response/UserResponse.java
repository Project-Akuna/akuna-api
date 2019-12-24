package ph.com.adcpp.commons.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Choy
 * @date 12/20/2019.
 */

@Getter
@Setter
public class UserResponse {
    private Long id;
    private String username;
    private List<UserResponse> downlines;
}
