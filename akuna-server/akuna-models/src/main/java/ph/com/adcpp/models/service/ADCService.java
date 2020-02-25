package ph.com.adcpp.models.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ph.com.adcpp.commons.constant.RoleConstant;
import ph.com.adcpp.commons.request.ADCRequest;
import ph.com.adcpp.models.entity.*;
import ph.com.adcpp.models.repository.ADCRepository;
import ph.com.adcpp.models.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Choy
 * @date 12/12/2019.
 */

@Slf4j
@Service
public class ADCService {

    private ADCRepository adcRepository;
    private ObjectMapper mapper;
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    public ADCService(ADCRepository adcRepository, ObjectMapper mapper,
                      UserService userService) {
        this.adcRepository = adcRepository;
        this.mapper = mapper;
        this.userService = userService;
    }

    public ADC save(ADC adc) {
        return adcRepository.save(adc);
    }

    @Transactional(rollbackOn = Exception.class)
    public ADC save(ADCRequest request) {
        log.info("Saving new ADC [{}] ...", request.getName());

        ADC adc = mapper.convertValue(request, ADC.class);
        adcRepository.save(adc);
        userService.save(createNewUserForADC(request));

        log.info("New ADC successfully saved.");
        return adc;
    }

    public List<ADCRequest> getAllADCs() {
        log.info("Getting all ADCs...");

        List<ADCRequest> adcRequests = new ArrayList<>();
        adcRepository.findAll().forEach(adc ->
                adcRequests.add(mapper.convertValue(adc, ADCRequest.class)));

        log.info("Found [{}] ADCs ", adcRequests.size());
        return adcRequests;
    }

    public User createNewUserForADC(ADCRequest request) {

        User user = mapper.convertValue(request, User.class);
        user.addRole(new Role(RoleConstant.ADC));
        user.setAddress(request.getOfficeAddress());
        user.setIsEnabled(true);
        user.setUsername(request.getUsername());
        user.setPassword(userService.getPasswordEncoder().encode(request.getPassword()));
        user.setTreeLevel(null);

        return user;
    }

    public void createNewUserForADC(ADC adc, String username, String password) {

        User user = new User();
        user.addRole(new Role(RoleConstant.ADC));
        user.setAddress(adc.getOfficeAddress());
        user.setIsEnabled(true);
        user.setUsername(username);
        user.setPassword(userService.getPasswordEncoder().encode(password));
        user.setTreeLevel(null);
        user.setCity(adc.getCity());
        user.setEmail(adc.getEmail());
        user.setMemberType(new MemberType(1L));

        userRepository.save(user);
    }
}
