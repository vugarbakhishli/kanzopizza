package az.crbn.common.security.auth.service;

import az.crbn.common.constants.HttpConstants;
import io.jsonwebtoken.Claims;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public final class TokenAuthService implements AuthService {

    private static final String AUTHORITY_CLAIM = "authority";
    private static final String USER_CLAIM = "userUuid";
    private final JwtService jwtService;

    @Override
    public Optional<Authentication> getAuthentication(HttpServletRequest req) {
        return Optional.ofNullable(req.getHeader(HttpConstants.AUTH_HEADER))
                .filter(this::isBearerAuth)
                .flatMap(this::getAuthenticationBearer);
    }

    private boolean isBearerAuth(String header) {
        return header.toLowerCase().startsWith(HttpConstants.BEARER_AUTH_HEADER.toLowerCase());
    }

    private Optional<Authentication> getAuthenticationBearer(String header) {
        var token = header.substring(HttpConstants.BEARER_AUTH_HEADER.length()).trim();
        var claims = jwtService.parseToken(token);
        log.trace("The claims parsed {}", claims);
        if (claims.getExpiration().before(new Date())) {
            return Optional.empty();
        }
        return Optional.of(getAuthenticationBearer(claims));
    }

    private Authentication getAuthenticationBearer(Claims claims) {
        var roles = List.of(claims.get(AUTHORITY_CLAIM, String.class));
        var authorityList = roles
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        var details = new CustomSpringSecurityUser(
                claims.getSubject(),
                "",
                authorityList,
                claims.get(USER_CLAIM, String.class)
        );

        return new UsernamePasswordAuthenticationToken(claims.getSubject(), details, authorityList);
    }

}
