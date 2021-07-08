package az.crbn.kanzopizza.ms.customer.service.dto;

import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressCreateReqDto {

    @NotBlank
    private String title;

    @Positive
    private BigDecimal latitude;

    @Positive
    private BigDecimal longitude;
}
