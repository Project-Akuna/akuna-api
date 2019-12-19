package ph.com.adcpp.commons.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Choy
 * @date 12/16/2019.
 */

@Getter
@Setter
public class EmailRequest {

    private String email;
    private String subject;
    private String body;
}
