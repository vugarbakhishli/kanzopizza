package az.crbn.kanzopizza.ms.auth.service.providers;

import az.crbn.common.security.auth.service.Claim;
import az.crbn.common.security.auth.service.ClaimProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AuthorityProvider implements ClaimProvider {
    private static final String RULE = "authority";

    @Override
    public Claim provide(Authentication authentication) {
        log.trace("Providing {} claims", RULE);
        String authority = authentication.getAuthorities().stream()
                .map(Object::toString)
                .findFirst()
                .orElse("");
        return new Claim(RULE, authority);
    }
}
