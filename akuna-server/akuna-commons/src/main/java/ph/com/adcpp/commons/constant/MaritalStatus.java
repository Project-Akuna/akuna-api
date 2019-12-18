package ph.com.adcpp.commons.constant;

/**
 * @author Choy
 * @date 12/18/2019.
 */
public enum MaritalStatus {

    SINGLE("Single"),
    MARRIED("Married"),
    WIDOWED("Widowed");

    private String text;

    MaritalStatus(String text) {
        this.text = text;
    }
}
