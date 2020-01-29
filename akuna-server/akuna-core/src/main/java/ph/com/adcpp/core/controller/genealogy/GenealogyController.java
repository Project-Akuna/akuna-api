package ph.com.adcpp.core.controller.genealogy;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ph.com.adcpp.commons.response.BaseResponse;
import ph.com.adcpp.models.service.UserService;
import ph.com.adcpp.utils.ResponseUtil;

/**
 * @author Choy
 * @date 1/29/2020.
 */

@RestController
@RequestMapping("/api/genealogy")
public class GenealogyController {

    private UserService userService;

    public GenealogyController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get-genealogy/{username}")
    public BaseResponse getUser(@PathVariable("username") String username) {
        return ResponseUtil.success(userService.getGenealogy(username));
    }
}
