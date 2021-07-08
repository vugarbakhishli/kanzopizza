package az.crbn.common.security.auth;

import az.crbn.common.security.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class AuthFilterConfigurerAdapter extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final List<AuthService> authServices;

    @Override
    public void configure(HttpSecurity http) {
        log.trace("Added auth request filter");
        http.addFilterBefore(new AuthRequestFilter(authServices), UsernamePasswordAuthenticationFilter.class);
    }
}
