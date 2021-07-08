package az.crbn.kanzopizza.ms.product.config;

import az.crbn.common.security.auth.service.JwtService;
import az.crbn.common.security.auth.service.TokenAuthService;
import az.crbn.common.security.config.SecurityProperties;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class TokenAuthConfiguration {

    @Bean
    public TokenAuthService tokenAuthService(JwtService jwtService) {
        return new TokenAuthService(jwtService);
    }

    @Bean
    public JwtService jwtService(@Autowired SecurityProperties securityProperties) {
        log.trace("Security properties {}", securityProperties);
        return new JwtService(Set.of(), securityProperties);
    }
}
