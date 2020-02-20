package ph.com.adcpp.models.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ph.com.adcpp.commons.constant.RoleConstant;
import ph.com.adcpp.commons.request.ADCRequest;
import ph.com.adcpp.models.entity.ADC;
import ph.com.adcpp.models.entity.RegistrationCode;
import ph.com.adcpp.models.entity.Role;
import ph.com.adcpp.models.entity.User;
import ph.com.adcpp.models.repository.ADCRepository;

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

    private User createNewUserForADC(ADCRequest request) {

        User user = mapper.convertValue(request, User.class);
        user.addRole(new Role(RoleConstant.ADC));
        user.setAddress(request.getOfficeAddress());
        user.setIsEnabled(true);
        user.setUsername(request.getName());
        user.setRegistrationCode(new RegistrationCode(request.getRegCode()));

        return user;
    }
}
