package ph.com.adcpp.models.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ph.com.adcpp.commons.constant.RoleConstant;
import ph.com.adcpp.commons.request.DepotRequest;
import ph.com.adcpp.commons.response.DepotResponse;
import ph.com.adcpp.models.entity.Depot;
import ph.com.adcpp.models.entity.Role;
import ph.com.adcpp.models.entity.User;
import ph.com.adcpp.models.repository.DepotRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DepotService {

    private DepotRepository depotRepository;
    private ObjectMapper mapper;
    private UserService userService;

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
        addDepotRoleToUser(request);
    }

    private void addDepotRoleToUser(DepotRequest request) {

        User user = userService.findByUsername(request.getUsername());
        user.addRole(new Role(RoleConstant.DEPOT));

        userService.save(user);
    }
}