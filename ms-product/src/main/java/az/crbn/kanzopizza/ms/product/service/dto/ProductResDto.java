package az.crbn.kanzopizza.ms.product.service.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResDto {
    private String uuid;
    private String title;
    private String description;
    private BigDecimal price;
}
