package az.crbn.kanzopizza.ms.customer.service.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResDto {
    private String uuid;
    private String title;
    private BigDecimal latitude;
    private BigDecimal longitude;
}
