package az.crbn.kanzopizza.ms.auth.service.dto.auth;

import static az.crbn.common.security.constants.AuthConstants.PASSWORD_MAX_LENGTH;
import static az.crbn.common.security.constants.AuthConstants.PASSWORD_MIN_LENGTH;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "password")
public class LoginRequestDto {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password;

    @NotNull
    private Boolean rememberMe;
}
