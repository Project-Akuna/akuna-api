package ph.com.adcpp.user.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ph.com.adcpp.user.model.AkunaUser;

import java.util.Collection;

/**
 * @Author raymond.galima
 * @date 12/7/2019.
 */
public class AkunaUserRepoUserDetails extends AkunaUser implements UserDetails{

    AkunaUserRepoUserDetails(AkunaUser user) {
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
