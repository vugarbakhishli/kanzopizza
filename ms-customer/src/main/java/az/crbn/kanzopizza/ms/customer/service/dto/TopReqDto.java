package az.crbn.kanzopizza.ms.customer.service.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopReqDto {
    @NotBlank
    private String fullName;

    @NotNull
    @Size(min = 16, max = 16)
    private String cardNumber;

    @NotNull
    private LocalDate expDate;

    @NotNull
    private Integer securityCode;

    @NotNull
    @Positive
    private BigDecimal amount;

    @NotNull
    private BigDecimal dummyCardBalance;
}
