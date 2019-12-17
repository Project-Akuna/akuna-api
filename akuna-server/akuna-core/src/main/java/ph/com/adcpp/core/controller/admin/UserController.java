package ph.com.adcpp.core.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ph.com.adcpp.commons.response.BaseResponse;
import ph.com.adcpp.user.request.UserRequest;
import ph.com.adcpp.user.service.UserService;
import ph.com.adcpp.utils.ResponseUtil;

/**
 * @author raymond.galima
 * @date 12/7/2019.
 */
@Slf4j
@RestController
@RequestMapping("/api/admin/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public BaseResponse saveUser(@RequestBody UserRequest request) {

        log.info("Saving new user [{}]", request.getUsername());
        userService.save(request);
        log.info("User [{}] successfully saved.", request.getUsername());

        return ResponseUtil.success();
    }
}