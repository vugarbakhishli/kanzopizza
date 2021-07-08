package az.crbn.common.security.auth.service;

import org.springframework.security.core.Authentication;

public interface ClaimProvider {

    Claim provide(Authentication authentication);

}
