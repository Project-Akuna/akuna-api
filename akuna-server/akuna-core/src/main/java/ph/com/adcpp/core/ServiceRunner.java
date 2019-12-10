package ph.com.adcpp.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**
 * @author raymond.galima
 * @date 12/3/2019.
 */
@SpringBootApplication
@ComponentScan(basePackages = "ph.com.adcpp")
@EntityScan(basePackages = "ph.com.adcpp")
@EnableJpaRepositories(basePackages = "ph.com.adcpp")
@EnableWebSecurity
public class ServiceRunner {

    public static void main(String[] args) {
        SpringApplication.run(ServiceRunner.class);
    }
}
