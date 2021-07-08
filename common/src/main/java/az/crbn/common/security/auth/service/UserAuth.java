package az.crbn.common.security.auth.service;

import az.crbn.common.security.constants.UserAuthority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAuth {
    private String uuid;
    private UserAuthority authority;
}
