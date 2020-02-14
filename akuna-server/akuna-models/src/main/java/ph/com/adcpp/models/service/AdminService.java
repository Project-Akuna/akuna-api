package ph.com.adcpp.models.service;

import org.springframework.stereotype.Service;
import ph.com.adcpp.models.repository.UserRepository;

@Service
public class AdminService {

    private UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


}
