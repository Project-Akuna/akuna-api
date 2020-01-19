package ph.com.adcpp.core.controller.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ph.com.adcpp.commons.response.BaseResponse;
import ph.com.adcpp.commons.request.UserRequest;
import ph.com.adcpp.models.service.UserService;
import ph.com.adcpp.utils.ResponseUtil;

import javax.validation.Valid;

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
    public BaseResponse saveUser(@RequestBody @Valid UserRequest request) {
        log.info("Saving new user [{}]", request.getUsername());

        userService.save(request);

        log.info("User [{}] successfully saved.", request.getUsername());
        return ResponseUtil.success();
    }

    @GetMapping("/get-user/{username}")
    public BaseResponse getUser(@PathVariable("username") String username) {
        return ResponseUtil.success(userService.findByUsername(username));
    }
}