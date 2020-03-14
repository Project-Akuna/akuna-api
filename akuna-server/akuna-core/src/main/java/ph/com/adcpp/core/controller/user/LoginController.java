package ph.com.adcpp.core.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ph.com.adcpp.commons.response.BaseResponse;
import ph.com.adcpp.commons.response.LoginResponse;
import ph.com.adcpp.models.service.InitialService;
import ph.com.adcpp.utils.ResponseUtil;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;

/**
 * @author Choy
 * @date 1/25/2020.
 */

@RestController
@RequestMapping("/api/entry")
public class LoginController {

    @Autowired
    private InitialService initialService;

    @PostMapping("/login")
    public BaseResponse login(Principal principal, HttpSession session, Authentication authentication) {

        LoginResponse response = new LoginResponse();
        response.setUsername(principal.getName());
        response.setSessionId(session.getId());
        response.setRoles(new ArrayList<>(authentication.getAuthorities()));

        return ResponseUtil.success(response);
    }

    @PostMapping("/build-initial-data")
    public BaseResponse buildInitialData() {
        initialService.buildInitialData();
        return ResponseUtil.success();
    }
}
