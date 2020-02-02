package ph.com.adcpp.models.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ph.com.adcpp.models.builder.GenealogyBuilder;
import ph.com.adcpp.models.entity.User;
import ph.com.adcpp.models.repository.UserRepository;
import ph.com.adcpp.commons.request.UserRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        log.info("Saving new user [{}]", user.getUsername());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);

        log.info("User [{}] successfully saved.", user.getUsername());
        return user;
    }

    public User save(UserRequest request) {
        return userRepository.save(convert(request));
    }

    public List<User> saveAll(List<UserRequest> userRequests) {
        List<User> users = new ArrayList<>();

        userRequests.forEach(user -> users.add(mapper.convertValue(user, User.class)));
        return userRepository.saveAll(users);
    }

    private User convert(UserRequest request) {
        log.info("Saving new user [{}]", request.getUsername());

        User user = mapper.convertValue(request, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        decideImmediateUpline(user);

        log.info("User [{}] successfully saved.", user.getUsername());
        return user;
    }

    private void decideImmediateUpline(User user) {
        User topLine = userRepository.findByAdcAndTreeLevel(user.getAdc(), 0).get(0);

        if (topLine.getDownlines().size() < 3) {

            user.setUpline(topLine);
            user.setTreeLevel(1);
            topLine.getDownlines().add(user);

            userRepository.save(user);
            userRepository.save(topLine);
        } else {

            int currentTreeLevel = 1;
            while (getUpline(user, currentTreeLevel)) {
                currentTreeLevel++;
            }
        }
    }

    private boolean getUpline(User user, int currentTreeLevel) {
        List<User> membersInTreeLevel =
                userRepository.findByAdcAndTreeLevel(user.getAdc(), currentTreeLevel);

        Optional<User> optional =
                membersInTreeLevel.stream().filter(member -> member.getDownlines().size() < 3)
                        .findAny();

        if (optional.isPresent()) {

            User upline = optional.get();
            user.setUpline(upline);
            user.setTreeLevel(currentTreeLevel + 1);
            upline.getDownlines().add(user);

            userRepository.save(user);
            userRepository.save(upline);
            return false;
        }

        return true;
    }

    public List<GenealogyBuilder> getGenealogy(String username) {

        User user = userRepository.findByUsername(username);

        List<GenealogyBuilder> userList = new ArrayList<>();
        userList.add(GenealogyBuilder.create(user));
        GenealogyBuilder.buildGenealogy(user, userList);

        return userList;
    }
}
