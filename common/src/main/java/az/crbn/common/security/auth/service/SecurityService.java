package az.crbn.common.security.auth.service;

import az.crbn.common.security.constants.UserAuthority;
import az.crbn.common.security.exception.UserNotFoundException;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class SecurityService {

    /**
     * Get the email of the current user.
     *
     * @return the email of the current user.
     */
    public Optional<String> getCurrentUserEmail() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
                .map(authentication -> {
                    if (authentication.getPrincipal() instanceof UserDetails) {
                        UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                        return springSecurityUser.getUsername();
                    } else if (authentication.getPrincipal() instanceof String) {
                        return (String) authentication.getPrincipal();
                    }
                    return null;
                });
    }

    /**
     * Get UUID of the current user.
     *
     * @return UUID of the current user.
     */
    public Optional<String> getCurrentUserUuid() {
        SecurityContext securityContext = SecurityContextHolder.getContext();

        return Optional.ofNullable(securityContext.getAuthentication())
                .map(authentication -> (CustomSpringSecurityUser) authentication.getCredentials())
                .map(CustomSpringSecurityUser::getUserUuid);
    }

    /**
     * Get the authority of the current user.
     *
     * @return the authority of the current user.
     */
    public Optional<UserAuthority> getCurrentUserAuthority() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return getAuthority(securityContext.getAuthentication());
    }

    /**
     * Get UserAuth of the current user.
     *
     * @return UserAuth of the current user.
     */
    public UserAuth getUserAuth() {
        return UserAuth
                .builder()
                .uuid(getCurrentUserUuid().orElseThrow(UserNotFoundException::new))
                .authority(getCurrentUserAuthority().orElseThrow(UserNotFoundException::new))
                .build();
    }

    /**
     * Get the JWT of the current user.
     *
     * @return the JWT of the current user.
     */
    public Optional<String> getCurrentUserJwt() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
                .filter(authentication -> authentication.getCredentials() instanceof String)
                .map(authentication -> (String) authentication.getCredentials());
    }

    /**
     * If the current user has a specific authority (security role). The name of this method comes from the {@code
     * isUserInRole()} method in the Servlet API.
     *
     * @param authority the authority to check.
     * @return true if the current user has the authority, false otherwise.
     */
    public boolean isCurrentUserInRole(UserAuthority authority) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (authentication != null)
                && getAuthority(authentication).stream().anyMatch(authority::equals);
    }

    private Optional<UserAuthority> getAuthority(Authentication authentication) {
        return authentication.getAuthorities()
                .stream()
                .map(Object::toString)
                .findFirst()
                .map(UserAuthority::valueOf);
    }
}
