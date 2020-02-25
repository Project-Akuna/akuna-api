package ph.com.adcpp.models.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ph.com.adcpp.commons.constant.RoleConstant;
import ph.com.adcpp.commons.request.UserRequest;
import ph.com.adcpp.commons.response.UserResponse;
import ph.com.adcpp.models.builder.PaginationBuilder;
import ph.com.adcpp.models.entity.Role;
import ph.com.adcpp.models.entity.User;
import ph.com.adcpp.models.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(rollbackOn = Exception.class)
public class CashierService {

    private UserRepository userRepository;
    private PaginationBuilder builder;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public CashierService(UserRepository userRepository, PaginationBuilder builder) {
        this.userRepository = userRepository;
        this.builder = builder;
    }

    public void save(UserRequest request) {
        log.info("Saving cashier [{}]...", request.getUsername());

        User user = builder.mapObject(request, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.addRole(new Role(RoleConstant.CASHIER));
        userRepository.save(user);

        log.info("Cashier [{}] successfully saved.", request.getUsername());
    }

    public List<UserResponse> getCashiers() {
        final Long roleId = 3L;
        return userRepository.getUsersByRoleId(roleId)
                .stream().map(cashier -> builder.mapObject(cashier, UserResponse.class))
                .collect(Collectors.toList());
    }
}
