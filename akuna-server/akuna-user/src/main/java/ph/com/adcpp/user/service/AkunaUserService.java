package ph.com.adcpp.user.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ph.com.adcpp.user.model.AkunaUser;
import ph.com.adcpp.user.repository.AkunaUserRepository;
import ph.com.adcpp.user.request.AkunaUserRequest;

import java.util.List;

/**
 * @Author raymond.galima
 * @date 12/3/2019.
 */
@Slf4j
@Service
public class AkunaUserService {

    private AkunaUserRepository akunaUserRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public AkunaUserService(AkunaUserRepository akunaUserRepository, BCryptPasswordEncoder passwordEncoder) {
        this.akunaUserRepository = akunaUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<AkunaUser> findAll(){
        return akunaUserRepository.findAll();
    }

    public AkunaUser save(AkunaUser user) {
        return akunaUserRepository.save(user);
    }

    public AkunaUser save(AkunaUserRequest request){
        return akunaUserRepository.save(convert(request));
    }

    private AkunaUser convert(AkunaUserRequest request) {
        AkunaUser user = new AkunaUser();
        BeanUtils.copyProperties(request, user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return user;
    }
}
