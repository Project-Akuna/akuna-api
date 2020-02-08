package ph.com.adcpp.models.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ph.com.adcpp.models.repository.UserRepository;

@Slf4j
@Service
public class CashierService {

    private UserRepository userRepository;

    public CashierService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
