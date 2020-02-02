package ph.com.adcpp.commons.constant;

/**
 * @author Choy
 * @date 1/29/2020.
 */
public enum IncentiveType {

    DRI("Direct Referral Incentive"),
    PLA("PayLite Something");

    private String description;

    IncentiveType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
