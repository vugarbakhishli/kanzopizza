package az.crbn.kanzopizza.ms.product.service.dto;

import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateReqDto {
    @NotBlank
    private String title;
    private String description;

    @NotNull
    @Positive
    private BigDecimal price;
}
