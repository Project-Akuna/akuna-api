package ph.com.adcpp.models.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Choy
 * @date 12/12/2019.
 */

@Getter
@Setter
@Entity
@Table(name = "TBL_MEMBER")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "MEMBER_OWNER")
    private Long owner;

    @Column(name = "MEMBER_LINKED_BY")
    private Long linkedBy;

    @Column(name = "MEMBER_FIRST_NAME")
    private String firstName;

    @Column(name = "MEMBER_MIDDLE_NAME")
    private String middleName;

    @Column(name = "MEMBER_LAST_NAME")
    private String lastName;

    @Column(name = "MEMBER_EMAIL")
    private String email;

    @Column(name = "MEMBER_ADC_CODE")
    private String adcCode;

    public Member(Long id) {
        this.id = id;
    }

    public Member() {
    }
}
