package az.crbn.kanzopizza.ms.order.service.dto;

import az.crbn.kanzopizza.ms.order.domain.enums.OrderStatus;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResDto {
    private String uuid;
    private Set<OrderProductResDto> products;
    private String customerUuid;
    private Instant lastModifiedDate;
    private OrderStatus status;
    private String addressUuid;
    private BigDecimal totalAmount;
}
