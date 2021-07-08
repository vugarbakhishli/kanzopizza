package az.crbn.kanzopizza.ms.order.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductResDto {
    private String uuid;
    private String productUuid;
    private Integer count;
}
