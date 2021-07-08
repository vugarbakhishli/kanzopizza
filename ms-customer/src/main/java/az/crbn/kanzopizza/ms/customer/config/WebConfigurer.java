package az.crbn.kanzopizza.ms.customer.config;

import az.crbn.common.security.config.SecurityProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class WebConfigurer {
    private final SecurityProperties properties;

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = properties.getCors();
        if (config.getAllowedOrigins() != null && !config.getAllowedOrigins().isEmpty()) {
            log.debug("Registering CORS filter");
            source.registerCorsConfiguration("/**", config);
        }
        return new CorsFilter(source);
    }
}
