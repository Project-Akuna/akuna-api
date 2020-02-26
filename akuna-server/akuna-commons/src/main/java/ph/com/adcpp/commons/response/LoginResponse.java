package ph.com.adcpp.commons.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * @author Choy
 * @date 1/25/2020.
 */

@Getter
@Setter
public class LoginResponse {

    private Long id;
    private String username;
    private String sessionId;
    private List<GrantedAuthority> roles;
}
