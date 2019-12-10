package ph.com.adcpp.user.request;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import ph.com.adcpp.user.model.Role;

import javax.validation.constraints.Pattern;
import java.util.Set;

/**
 * @Author raymond.galima
 * @date 12/7/2019.
 */

@Getter
@Setter
public class AkunaUserRequest {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String fullName;

    @NotEmpty
    private Boolean isEnabled;

    @NotEmpty
    @Pattern(regexp = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$")
    private String email;

    @NotEmpty
    private Set<Role> roles;
}
