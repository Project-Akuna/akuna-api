package ph.com.adcpp.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.stream.Stream;

@Configuration
public abstract class RouterConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        /*
         * 	Set all path that is not index, index.html or api
         *   to route to VueJS front end.
         *
         *   I Also put assets paths (js, css, img) here so that index.html can route on its assets :)
         * */
        registry.addViewController("/{_:^(?!index\\.html|api|js|css|img).*$}")
                .setViewName("forward:/");
        registry.addViewController("/{_:^(?!index\\.html|oauth|api|js|css|img).*$}/**")
                .setViewName("forward:/");
    }
}

@Configuration
@Primary
@Profile(value = {"local", "standalone"})
abstract class LocalRoutesConfig extends RouterConfig {

    @Value(value = "${cors.urls}")
    private String corsUrls;

    @Value(value = "${cors.origin}")
    private String origin;

    @Value(value = "${cors.allowed.methods}")
    private String corsAllowedMethods;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String comma = ",";
        Stream.of(corsUrls.split(comma))
                .forEach(url -> registry.addMapping(url)
                        .allowedOrigins(origin)
                        .allowedMethods(corsAllowedMethods.split(comma)));
    }
}
