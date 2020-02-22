package ph.com.adcpp.commons.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepotResponse {

    private Long id;
    private String name;
    private String code;
    private String officeAddress;
    private CityResponse city;
    private String landLineNumber1;
    private String landLineNumber2;
    private String mobileNumber1;
    private String mobileNumber2;
    private String email;
}
