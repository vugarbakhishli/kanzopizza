package az.crbn.kanzopizza.ms.auth.security;

import az.crbn.common.security.auth.service.CustomSpringSecurityUser;
import az.crbn.common.security.constants.UserStatus;
import az.crbn.kanzopizza.ms.auth.domain.User;
import az.crbn.kanzopizza.ms.auth.exception.UserIsNotActiveException;
import az.crbn.kanzopizza.ms.auth.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.trace("Authenticating {}", email);

        return userRepository.findByEmail(email)
                .map(this::createSpringSecurityUser)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format("User %s was not found in the database", email)));
    }

    private CustomSpringSecurityUser createSpringSecurityUser(User user) {
        checkUserProfileStatus(user);
        List<GrantedAuthority> grantedAuthorities = List.of(new SimpleGrantedAuthority(user.getAuthority().name()));

        return new CustomSpringSecurityUser(
                user.getEmail(),
                user.getPassword(),
                grantedAuthorities,
                user.getUuid()
        );
    }

    private void checkUserProfileStatus(User user) throws UserIsNotActiveException {
        if (user.getStatus() != UserStatus.ACTIVE) {
            throw new UserIsNotActiveException();
        }
    }
}
