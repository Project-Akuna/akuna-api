package ph.com.adcpp.commons.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author Choy
 * @date 12/12/2019.
 */

@Getter
@Setter
public class ADCRequest {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String code;

    @NotNull(message = "Office address is required")
    private String officeAddress;

    @NotNull
    private CityRequest city;

    @NotNull
    private String landLineNumber1;

    private String landLineNumber2;

    @NotNull
    private String mobileNumber1;

    private String mobileNumber2;

    @NotNull
    private String email;

    @NotNull
    private Boolean isSelling;

    @NotNull
    private String username;

    @NotNull
    private String password;
}
