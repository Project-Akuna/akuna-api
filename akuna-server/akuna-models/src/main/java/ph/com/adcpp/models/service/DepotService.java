package ph.com.adcpp.models.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ph.com.adcpp.commons.constant.MaritalStatus;
import ph.com.adcpp.commons.constant.RoleConstant;
import ph.com.adcpp.commons.request.DepotRequest;
import ph.com.adcpp.commons.response.DepotResponse;
import ph.com.adcpp.models.entity.*;
import ph.com.adcpp.models.repository.DepotRepository;
import ph.com.adcpp.models.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DepotService {

    private DepotRepository depotRepository;
    private ObjectMapper mapper;
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    public DepotService(DepotRepository depotRepository, ObjectMapper mapper, UserService userService) {
        this.depotRepository = depotRepository;
        this.mapper = mapper;
        this.userService = userService;
    }

    public List<DepotResponse> getAllDepot() {
        List<DepotResponse> depotResponses = new ArrayList<>();

        depotRepository.findAll().forEach(depot -> depotResponses.add(mapper.convertValue(depot, DepotResponse.class)));

        return depotResponses;
    }

    public void save(DepotRequest request) {
        Depot depot = mapper.convertValue(request, Depot.class);
        depotRepository.save(depot);
    }

    public Depot save(Depot depot) {
        return depotRepository.save(depot);
    }

    public void createUserForDepot(DepotRequest request) {

        User user = new User();
        user.setUsername(request.getUsername());
        user.addRole(new Role(RoleConstant.DEPOT));
        user.setPassword(userService.getPasswordEncoder().encode(request.getPassword()));
        user.setIsEnabled(true);
        user.setAddress(request.getOfficeAddress());
        user.setCity(mapper.convertValue(request.getCityRequest(), City.class));
        user.setEmail(request.getEmail());

        userRepository.save(user);
    }

    public void createUser(Depot depot, String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(userService.getPasswordEncoder().encode(password));
        user.addRole(new Role(RoleConstant.DEPOT));
        user.setIsEnabled(true);
        user.setAddress(depot.getOfficeAddress());
        user.setCity(depot.getCity());
        user.setEmail(depot.getEmail());
        user.setMaritalStatus(MaritalStatus.SINGLE);
        user.setMemberType(new MemberType(1L));

        userRepository.save(user);
    }
}