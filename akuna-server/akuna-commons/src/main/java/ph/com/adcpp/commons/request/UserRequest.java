package ph.com.adcpp.commons.request;

import lombok.Getter;
import lombok.Setter;

import ph.com.adcpp.commons.constant.MaritalStatus;
import ph.com.adcpp.commons.constant.Relationship;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;


/**
 * @author raymond.galima
 * @date 12/7/2019.
 */

@Getter
@Setter
public class UserRequest {

    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private Boolean isEnabled;

    @NotNull
    private String email;

    private UserRequest upline;

    private UserRequest directSponsor;

    @NotNull
    private LocalDate birthday;

    @NotNull
    private ADCRequest adc;

    @NotNull
    private String address;

    @NotNull
    private MaritalStatus maritalStatus;

    private String spouse;

    private String successor;

    private Relationship relationship;

    @NotNull
    private CityRequest city;

    @NotNull
    private Set<RoleRequest> roles;

    private String regCode;

    private Integer numberOfAccounts;
}
