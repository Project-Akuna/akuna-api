package ph.com.adcpp.models.service;

import org.springframework.stereotype.Service;
import ph.com.adcpp.commons.constant.MaritalStatus;
import ph.com.adcpp.commons.constant.RoleConstant;
import ph.com.adcpp.commons.request.ADCRequest;
import ph.com.adcpp.commons.request.CityRequest;
import ph.com.adcpp.commons.request.DepotRequest;
import ph.com.adcpp.models.entity.*;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@Transactional(rollbackOn = Exception.class)
public class InitialService {

    private UserService userService;
    private DepotService depotService;
    private ADCService adcService;

    public InitialService(UserService userService, DepotService depotService, ADCService adcService) {
        this.userService = userService;
        this.depotService = depotService;
        this.adcService = adcService;
    }

    public void buildInitialData() {
        User sysAdmin = createAdmin();
        Depot depot = createDepot(sysAdmin);
        createADC(depot, sysAdmin);
        userService.generateCompanyUsers();
    }

    private User createAdmin() {
        User sysAdmin = new User();
        sysAdmin.setUsername("SysAdmin");
        sysAdmin.setPassword("admin");
        sysAdmin.setFirstName("Dina");
        sysAdmin.setMiddleName("R");
        sysAdmin.setLastName("De Jesus");
        sysAdmin.setCity(new City(1367L));
        sysAdmin.setMemberType(new MemberType(2L));
        sysAdmin.addRole(new Role(RoleConstant.SYSADMIN));
        sysAdmin.setMaritalStatus(MaritalStatus.MARRIED);
        sysAdmin.setBirthday(LocalDate.now());
        sysAdmin.setAddress("Quezon City");
        sysAdmin.setIsEnabled(true);
        sysAdmin.setEmail("test@gmail.com");
        sysAdmin.setWallet(new Wallet(sysAdmin));

        return userService.save(sysAdmin);
    }

    private Depot createDepot(User owner) {
        Depot depot = new Depot();
        depot.setCity(owner.getCity());
        depot.setCode("10001");
        depot.setEmail("depot@gmail.com");
        depot.setIsSelling(true);
        depot.setLandLineNumber1("028123");
        depot.setMobileNumber1("09123456789");
        depot.setName("DEPOT QC");
        depot.setOfficeAddress(owner.getAddress());
        depot.setOwner(owner);
        depot.setCity(new City(1367L));
        depot.setWallet(new Wallet(depot));

        depotService.createUser(depot, "qc_depot", "admin");
        return depotService.save(depot);
    }

    private ADC createADC(Depot ownerDepot, User userOwner) {
        ADC adc = new ADC();
        adc.setCity(ownerDepot.getCity());
        adc.setCode("10001");
        adc.setEmail("adc@gmail.com");
        adc.setIsSelling(true);
        adc.setLandLineNumber1(ownerDepot.getLandLineNumber1());
        adc.setMobileNumber1(ownerDepot.getMobileNumber1());
        adc.setOfficeAddress(ownerDepot.getOfficeAddress());
        adc.setOwner(userOwner);
        adc.setWallet(new Wallet(adc));
        adc.setCity(new City(1367L));
        adc.setName("QC ADC");

        adcService.createNewUserForADC(adc, "qc_adc", "admin");
        return adcService.save(adc);
    }
}
