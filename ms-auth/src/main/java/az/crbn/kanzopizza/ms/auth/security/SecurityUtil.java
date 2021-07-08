package az.crbn.kanzopizza.ms.auth.security;

import az.crbn.common.security.auth.service.JwtService;
import az.crbn.common.security.auth.service.SecurityService;
import az.crbn.common.security.exception.UserNotFoundException;
import az.crbn.kanzopizza.ms.auth.domain.User;
import az.crbn.kanzopizza.ms.auth.repository.UserRepository;
import az.crbn.kanzopizza.ms.auth.service.dto.auth.LoginRequestDto;
import java.time.Duration;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Import(SecurityService.class)
public class SecurityUtil {
    private static final Duration ONE_DAY = Duration.ofDays(1);
    private static final Duration ONE_WEEK = Duration.ofDays(7);

    private final UserRepository userRepository;
    private final SecurityService securityService;
    private final JwtService jwtService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public User getCurrentUser() {
        return securityService.getCurrentUserEmail()
                .map(userRepository::findByEmail)
                .map(Optional::get)
                .orElseThrow(UserNotFoundException::new);
    }

    public String createAuthentication(LoginRequestDto loginRequestDto) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtService.issueToken(authentication, getDuration(loginRequestDto.getRememberMe()));
    }

    private Duration getDuration(Boolean rememberMe) {
        return ((rememberMe != null) && rememberMe) ? ONE_WEEK : ONE_DAY;
    }
}
