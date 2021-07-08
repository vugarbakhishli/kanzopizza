package az.crbn.kanzopizza.ms.auth.config;


import az.crbn.common.security.auth.service.JwtService;
import az.crbn.common.security.auth.service.TokenAuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TokenAuthConfiguration {

    @Bean
    public TokenAuthService tokenAuthService(JwtService jwtService) {
        return new TokenAuthService(jwtService);
    }
}
