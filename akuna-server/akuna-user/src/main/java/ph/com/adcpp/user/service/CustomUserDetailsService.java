package ph.com.adcpp.user.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ph.com.adcpp.user.model.User;
import ph.com.adcpp.user.repository.UserRepository;

import java.util.Objects;

/**
 * @Author raymond.galima
 * @date 12/7/2019.
 */

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService{

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) {

        User user = userRepository.findByUsername(s);

        if(Objects.isNull(user)) {
            log.error("User [{}] doesn't exist.", s);
            throw new UsernameNotFoundException(String.format("User [%s] doesn't exist.", s));
        }

        return new UserRepoUserDetails(user);
    }
}
