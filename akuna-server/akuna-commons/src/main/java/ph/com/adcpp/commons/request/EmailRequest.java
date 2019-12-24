package ph.com.adcpp.commons.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author Choy
 * @date 12/16/2019.
 */

@Getter
@Setter
public class EmailRequest {

    @NotNull
    private String email;

    @NotNull
    private String subject;

    @NotNull
    private String body;
}
