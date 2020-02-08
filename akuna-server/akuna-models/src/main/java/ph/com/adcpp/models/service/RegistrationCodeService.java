package ph.com.adcpp.models.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ph.com.adcpp.commons.request.RegistrationCodeRequest;
import ph.com.adcpp.commons.response.RegistrationCodeResponse;
import ph.com.adcpp.models.entity.ADC;
import ph.com.adcpp.models.entity.RegistrationCode;
import ph.com.adcpp.models.repository.RegistrationCodeRepository;
import ph.com.adcpp.models.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Choy
 * @date 12/17/2019.
 */
@Slf4j
@Service
public class RegistrationCodeService {

    private RegistrationCodeRepository codeRepository;
    private ObjectMapper mapper;

    public RegistrationCodeService(RegistrationCodeRepository codeRepository,
                                    ObjectMapper mapper) {
        this.codeRepository = codeRepository;
        this.mapper = mapper;
    }

    public List<RegistrationCode> findAll() {
        return codeRepository.findAll();
    }

    public RegistrationCode save(RegistrationCode code) {
        return codeRepository.save(code);
    }

    public List<RegistrationCode> save(List<RegistrationCode> codes) {
        return codeRepository.saveAll(codes);
    }

    public List<RegistrationCodeResponse> convertToList(List<RegistrationCode> codes) {
        log.info("Converting registration codes to response...");

        List<RegistrationCodeResponse> responses = new ArrayList<>();
        codes.forEach(code -> responses.add(mapper.convertValue(code, RegistrationCodeResponse.class)));

        log.info("Codes successfully converted.");
        return responses;
    }

    public List<RegistrationCode> generateCodes(RegistrationCodeRequest request) {
        log.info("Generating [{}] new codes...", request.getQuantity());

        List<RegistrationCode> registrationCodes = new ArrayList<>();

        for(int ctr = 0; ctr < request.getQuantity(); ctr++) {

            RegistrationCode registrationCode = new RegistrationCode();
            registrationCode.setCode(UUID.randomUUID().toString());
            registrationCode.setAdc(new ADC(request.getAdcId()));
            registrationCode.setAddedBy(new User(request.getAddedBy()));
            registrationCode.setAmount(request.getAmount());
            registrationCode.setOwner(new User(request.getOwner()));

            registrationCodes.add(registrationCode);
        }

        log.info("Successfully generated [{}] codes", request.getQuantity());
        return codeRepository.saveAll(registrationCodes);
    }

    public RegistrationCode findByCode(String code) {
        return codeRepository.findByCode(code);
    }
}
