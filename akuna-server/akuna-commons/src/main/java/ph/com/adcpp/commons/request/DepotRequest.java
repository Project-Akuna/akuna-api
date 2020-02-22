package ph.com.adcpp.commons.request;

import lombok.Getter;
import lombok.Setter;
import ph.com.adcpp.commons.response.DepotResponse;

@Getter
@Setter
public class DepotRequest extends DepotResponse {
    private String username;
    private String password;
}
