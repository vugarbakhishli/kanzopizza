package az.crbn.common.dto.customer;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterReqDto {

    @NotBlank
    private String userUuid;
}
