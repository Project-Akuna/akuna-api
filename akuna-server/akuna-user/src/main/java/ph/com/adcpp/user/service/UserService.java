package ph.com.adcpp.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ph.com.adcpp.user.model.User;
import ph.com.adcpp.user.repository.UserRepository;
import ph.com.adcpp.user.request.UserRequest;

import java.util.List;

/**
 * @author raymond.galima
 * @date 12/3/2019.
 */
@Slf4j
@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private ObjectMapper mapper;

    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder passwordEncoder,
                       ObjectMapper mapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User save(UserRequest request) {
        return userRepository.save(convert(request));
    }

    private User convert(UserRequest request) {
        User user = mapper.convertValue(request, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }
}
