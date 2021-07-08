package az.crbn.common.dto.product;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductPriceResDto {
    private String uuid;
    private BigDecimal price;
}
