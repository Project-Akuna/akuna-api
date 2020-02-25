package ph.com.adcpp.commons.request;

import lombok.Getter;
import lombok.Setter;
import ph.com.adcpp.commons.response.DepotResponse;

@Getter
@Setter
public class DepotRequest extends DepotResponse {
    private String username;
    private String password;
    private String officeAddress;
    private CityRequest cityRequest;

    public DepotRequest(String username, String password, String officeAddress, CityRequest cityRequest) {
        this.username = username;
        this.password = password;
        this.officeAddress = officeAddress;
        this.cityRequest = cityRequest;
    }

    public DepotRequest() {
    }
}
