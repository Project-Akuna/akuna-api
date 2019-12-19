package ph.com.adcpp.models.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ph.com.adcpp.models.entity.User;

import java.util.Collection;

/**
 * @author raymond.galima
 * @date 12/7/2019.
 */
public class UserRepoUserDetails extends User implements UserDetails{

    UserRepoUserDetails(User user) {
        super(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return getIsEnabled();
    }
}
