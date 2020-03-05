package ph.com.adcpp.models.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ph.com.adcpp.commons.constant.IncentiveStatus;
import ph.com.adcpp.commons.constant.IncentiveType;
import ph.com.adcpp.commons.constant.MaritalStatus;
import ph.com.adcpp.commons.constant.RoleConstant;
import ph.com.adcpp.commons.request.PaginatedRequest;
import ph.com.adcpp.commons.response.UserResponse;
import ph.com.adcpp.models.builder.GenealogyBuilder;
import ph.com.adcpp.models.builder.PaginationBuilder;
import ph.com.adcpp.models.entity.*;
import ph.com.adcpp.models.repository.IncentiveRepository;
import ph.com.adcpp.models.repository.UserRepository;
import ph.com.adcpp.commons.request.UserRequest;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;
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

    @Autowired
    private IncentiveRepository incentiveRepository;

    @Autowired
    private PaginationBuilder pageBuilder;

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

    public void save(UserRequest request) {
        log.info("Saving new user [{}]", request.getUsername());

        User user = convert(request);
        if (Objects.nonNull(request.getRegCode())) {
            user.setRegistrationCode(updateRegistrationCode(user, request.getRegCode()));
        }
        user.setWallet(new Wallet(user));
        userRepository.save(user);

        User upline = userRepository.getOne(user.getUpline().getId());
        upline.getDownlines().add(user);
        userRepository.save(upline);

        addUserToDRI(user.getDirectSponsor());
        log.info("User [{}] successfully saved.", user.getUsername());
    }

    public void generateCompanyUsers() {
        String username = "wc_";
        Integer userNumber = 1;

        while (userNumber != 41) {

            String name = username + userNumber;
            User user = new User();
            user.setUsername(name);
            user.setPassword(passwordEncoder.encode("changeit"));
            user.setFirstName("Wellness");
            user.setLastName("Connection");
            user.setIsEnabled(true);
            user.setEmail(name + "@gmail.com");
            user.setBirthday(LocalDate.now());
            user.setAddress("QC");
            user.setMaritalStatus(MaritalStatus.SINGLE);
            user.setCity(new City(1367L));
            user.addRole(new Role(RoleConstant.MEMBER));
            user.setWallet(new Wallet(user));
            decideImmediateUpline(user);
            user.setMemberType(new MemberType(3L));

            userRepository.save(user);

            userNumber++;
        }
    }

    public List<User> saveAll(List<UserRequest> userRequests) {
        List<User> users = new ArrayList<>();

        userRequests.forEach(user -> users.add(mapper.convertValue(user, User.class)));
        return userRepository.saveAll(users);
    }

    private User convert(UserRequest request) {

        User user = new User();
        user.setAdc(new ADC(request.getAdc().getId()));
        user.setAddress(request.getAddress());
        user.setBirthday(request.getBirthday());
        user.setCity(new City(request.getCity().getId()));
        user.setEmail(request.getEmail());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setMaritalStatus(request.getMaritalStatus());
        user.setIsEnabled(true);
        Role role = new Role(request.getRoles().stream().findAny().get().getId());
        user.addRole(role);
        user.setUsername(request.getUsername());
        user.setUpline(new User(request.getUpline().getId()));
        user.setDirectSponsor(new User(request.getDirectSponsor().getId()));
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setTreeLevel(userRepository.getOne(request.getUpline().getId()).getTreeLevel() + 1);

        return user;
    }

    private void decideImmediateUpline(User user) {
        Optional<User> optional = userRepository.findByTreeLevel(0).stream().findAny();

        if (!optional.isPresent()) {
            user.setTreeLevel(0);
        }
        else if (optional.get().getDownlines().size() < 3) {
            User topLine = optional.get();
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
        List<User> membersInTreeLevel = userRepository.findByTreeLevel(currentTreeLevel);

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

    @SuppressWarnings("unchecked")
    public List<UserResponse> getAllUsers() {
        log.info("Getting users...");
        return (List<UserResponse>) mapper.convertValue(userRepository.findAll(), List.class);
    }

    public List<UserResponse> findAll() {
        return userRepository.findAll().stream()
                .filter(user -> user.getDownlines().size() < 3 && user.getIsVisible())
                .map(this::mapUser).collect(Collectors.toList());
    }

    private UserResponse mapUser(User user) {
        return mapper.convertValue(user, UserResponse.class);
    }

    public List<UserResponse> getCashiers() {
        final Long roleId = 3L;
        return userRepository.getUsersByRoleId(roleId)
                .stream().map(this::mapUser).collect(Collectors.toList());
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public BCryptPasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    private void addUserToDRI(User user) {
        Incentive incentive = incentiveRepository.findByIncentiveTypeAndIncentiveStatus(IncentiveType.DRI, IncentiveStatus.ACTIVE);
        incentive.addUser(user);
        incentiveRepository.save(incentive);

        addToQualifiers(incentiveRepository.save(incentive), user);
    }

    private void addToQualifiers(Incentive incentive, User user) {
        Incentive q1 = incentiveRepository.findByIncentiveTypeAndIncentiveStatus(IncentiveType.Q1, IncentiveStatus.ACTIVE);
        Incentive q2 = incentiveRepository.findByIncentiveTypeAndIncentiveStatus(IncentiveType.Q2, IncentiveStatus.ACTIVE);
        Incentive q3 = incentiveRepository.findByIncentiveTypeAndIncentiveStatus(IncentiveType.Q3, IncentiveStatus.ACTIVE);

        long count = incentive.getUsers().stream().filter(user1 -> user1.getUsername().equals(user.getUsername()))
                .count();

        if(count > 2) {
            q1.addUser(user);
            incentiveRepository.save(q1);
        } else if (count == 2) {
            q2.addUser(user);
            incentiveRepository.save(q2);
        } else {
            q3.addUser(user);
            incentiveRepository.save(q3);
        }
    }
}
