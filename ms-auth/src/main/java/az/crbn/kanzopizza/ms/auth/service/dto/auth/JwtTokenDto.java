package az.crbn.kanzopizza.ms.auth.service.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtTokenDto {
    private String authToken;
    private String refreshToken;
}
