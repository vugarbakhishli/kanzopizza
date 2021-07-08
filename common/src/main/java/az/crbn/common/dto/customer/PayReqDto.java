package az.crbn.common.dto.customer;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PayReqDto {

    @NotNull
    @Positive
    private BigDecimal totalAmount;
}
