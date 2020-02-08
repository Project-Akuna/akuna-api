package ph.com.adcpp.models.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ph.com.adcpp.commons.request.PaginatedRequest;
import ph.com.adcpp.commons.response.UserResponse;
import ph.com.adcpp.models.builder.GenealogyBuilder;
import ph.com.adcpp.models.entity.RegistrationCode;
import ph.com.adcpp.models.entity.User;
import ph.com.adcpp.models.entity.Wallet;
import ph.com.adcpp.models.repository.UserRepository;
import ph.com.adcpp.commons.request.UserRequest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author raymond.galima
 * @date 12/3/2019.
 */
@Slf4j
@Service
@Transactional(rollbackOn = Exception.class)
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private ObjectMapper mapper;
    private RegistrationCodeService codeService;

    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder passwordEncoder,
                       ObjectMapper mapper, RegistrationCodeService codeService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
        this.codeService = codeService;
    }

    public User save(User user) {
        log.info("Saving new user [{}]", user.getUsername());

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);

        log.info("User [{}] successfully saved.", user.getUsername());
        return user;
    }

    public User save(UserRequest request) {

        User user = convert(request);
        if (Objects.nonNull(request.getRegCode())) {
            user.setRegistrationCode(updateRegistrationCode(user, request.getRegCode()));
        }
        user.setWallet(new Wallet(user));

        return userRepository.save(user);
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
        log.info("Building genealogy of user: [{}]", username);
        User user = userRepository.findByUsername(username);

        List<GenealogyBuilder> userList = new ArrayList<>();
        userList.add(GenealogyBuilder.create(user));
        GenealogyBuilder.buildGenealogy(user, userList);

        log.info("User [{}] has [{}] network", username, userList.size() - 1);
        return userList;
    }

    private RegistrationCode updateRegistrationCode(User user, String code) {
        RegistrationCode registrationCode = codeService.findByCode(code);
        registrationCode.setIsUsed(true);
        registrationCode.setSoldTo(user);
        return codeService.save(registrationCode);
    }

    public List<UserResponse> getAllUsers(PaginatedRequest request) {
        log.info("Getting users...");
        Sort sort;

        if (request.getSortValue().equals(Sort.Direction.DESC.name())) {
            sort = Sort.by(request.getSortKey()).descending();
        } else {
            sort = Sort.by(request.getSortKey()).ascending();
        }

        Pageable pageRequest = PageRequest.of(request.getPageIndex(), request.getPageSize(), sort);
        return userRepository.findAll(pageRequest).get()
                .map(this::mapUser)
                .collect(Collectors.toList());
    }

    private UserResponse mapUser(User user) {
        return mapper.convertValue(user, UserResponse.class);
    }

    public List<UserResponse> getCashiers() {
        final Long roleId = 3L;
        return userRepository.getUsersByRoleId(roleId)
                .stream().map(this::mapUser).collect(Collectors.toList());
    }
}
