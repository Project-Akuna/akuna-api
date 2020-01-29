package ph.com.adcpp.core.controller.admin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ph.com.adcpp.commons.response.BaseResponse;
import ph.com.adcpp.commons.response.LoginResponse;
import ph.com.adcpp.utils.ResponseUtil;

import javax.servlet.http.HttpSession;
import java.security.Principal;

/**
 * @author Choy
 * @date 1/25/2020.
 */

@RestController
@RequestMapping("/api")
public class LoginController {

    @PostMapping("/login")
    public BaseResponse login(Principal principal, HttpSession session) {

        LoginResponse response = new LoginResponse();
        response.setUsername(principal.getName());
        response.setSessionId(session.getId());

        return ResponseUtil.success(response);
    }
}
