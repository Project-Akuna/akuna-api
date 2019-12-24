package ph.com.adcpp.commons.constant;

import lombok.Getter;

/**
 * @author Choy
 * @date 12/19/2019.
 */

@Getter
public enum RoleConstant {

    SYSADMIN("ROLE_SYSADMIN", 1L),
    MEMBER("ROLE_MEMBER", 2L),
    CASHIER("ROLE_CASHIER", 3L),
    ADC("ROLE_ADC", 4L),
    DEPOT("ROLE_DEPOT", 5L);

    private String name;
    private Long id;

    RoleConstant(String name, Long id){
        this.name = name;
        this.id = id;
    }
}
