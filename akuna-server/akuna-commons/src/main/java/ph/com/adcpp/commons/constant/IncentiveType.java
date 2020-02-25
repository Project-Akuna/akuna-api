package ph.com.adcpp.commons.constant;

/**
 * @author Choy
 * @date 1/29/2020.
 */
public enum IncentiveType {

    Q1("Qualifier 1"),
    Q2("Qualifier 2"),
    Q3("Qualifier 3"),
    DRI("Direct Referral Incentive");

    private String description;

    IncentiveType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
