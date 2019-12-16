package ph.com.adcpp.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ph.com.adcpp.commons.constant.RoleConstant;
import ph.com.adcpp.user.service.CustomUserDetailsService;

/**
 * @author raymond.galima
 * @date 12/6/2019.
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/test/**", "/admin/user/save").permitAll()
                .antMatchers("/api", "/api/**").authenticated()
                .antMatchers("/products/**").hasAnyRole(RoleConstant.SYSADMIN,
                    RoleConstant.USER)
                .antMatchers("/admin/**").hasRole(RoleConstant.SYSADMIN)
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(getPasswordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
