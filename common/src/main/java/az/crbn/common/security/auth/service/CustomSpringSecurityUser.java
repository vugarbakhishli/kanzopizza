package az.crbn.common.security.auth.service;

import java.util.Collection;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomSpringSecurityUser extends User {

    @Setter
    @Getter
    private String userUuid;

    public CustomSpringSecurityUser(String email,
                                    String password,
                                    Collection<? extends GrantedAuthority> authorities,
                                    String userUuid) {
        super(email, password, authorities);
        this.userUuid = userUuid;
    }
}
