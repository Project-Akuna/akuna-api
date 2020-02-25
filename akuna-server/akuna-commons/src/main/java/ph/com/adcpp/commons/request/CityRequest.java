package ph.com.adcpp.commons.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Choy
 * @date 12/19/2019.
 */
@Getter
@Setter
public class CityRequest {

    private Long id;

    public CityRequest(Long id) {
        this.id = id;
    }

    public CityRequest() {
    }
}
