package ph.com.adcpp.core.controller.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ph.com.adcpp.commons.request.PaginatedRequest;
import ph.com.adcpp.commons.response.BaseResponse;
import ph.com.adcpp.commons.request.UserRequest;
import ph.com.adcpp.models.service.UserService;
import ph.com.adcpp.utils.ResponseUtil;

import javax.validation.Valid;
import java.util.List;

/**
 * @author raymond.galima
 * @date 12/7/2019.
 */
@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public BaseResponse saveUser(@RequestBody @Valid UserRequest request) {
        userService.validate(request);
        return ResponseUtil.success();
    }

    @PostMapping("/save-multiple-users")
    public BaseResponse saveMultipleUsers(@RequestBody @Valid List<UserRequest> requests) {
        log.info("Saving multiple users...");

        userService.saveAll(requests);

        log.info("Users successfully saved.");
        return ResponseUtil.success();
    }

    @PostMapping("/get-users")
    public BaseResponse getAllUsers() {
        return ResponseUtil.success(userService.findAll());
    }

    @PostMapping("/get-users-admin")
    public BaseResponse getAllUsersAdmin() {
        return ResponseUtil.success(userService.findAllForAdmin());
    }

    @GetMapping("/get-user/{username}")
    public BaseResponse findByUsername(@PathVariable String username) {
        return ResponseUtil.success(userService.findByUsername(username));
    }

    @PostMapping("/build-initial-tree")
    public BaseResponse buildInitialTree() {
        userService.generateCompanyUsers();
        return ResponseUtil.success();
    }

    @GetMapping("/get-all-visible")
    public BaseResponse getAllVisible() {
        return ResponseUtil.success(userService.getAllVisible());
    }

    @GetMapping("/get-wallet/{username}")
    public BaseResponse getWallet(@PathVariable String username) {
        return ResponseUtil.success(userService.getWallet(username));
    }
}