package az.crbn.kanzopizza.ms.order.config;

import static az.crbn.common.constants.HttpConstants.SUB_PATH;
import static az.crbn.common.security.constants.UserAuthority.ADMIN;
import static az.crbn.common.security.constants.UserAuthority.USER;
import static org.springframework.http.HttpMethod.GET;

import az.crbn.common.security.auth.AuthenticationEntryPointConfigurer;
import az.crbn.common.security.auth.service.AuthService;
import az.crbn.common.security.auth.service.JwtService;
import az.crbn.common.security.config.BaseSecurityConfig;
import az.crbn.common.security.config.SecurityProperties;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Slf4j
@Import({
        SecurityProperties.class, JwtService.class,
        AuthenticationEntryPointConfigurer.class
})
@EnableWebSecurity
public class SecurityConfiguration extends BaseSecurityConfig {
    private static final String ORDER_API = "/api/orders";

    public SecurityConfiguration(SecurityProperties securityProperties, List<AuthService> authServices) {
        super(securityProperties, authServices);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(GET, ORDER_API + "/v1/admin").access(authorities(ADMIN))
                .antMatchers(ORDER_API + SUB_PATH).access(authorities(USER));

        super.configure(http);
    }
}
