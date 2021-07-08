package az.crbn.kanzopizza.ms.order.service.dto;

import java.util.Set;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateReqDto {
    @NotNull
    @Size(min = 1)
    private Set<OrderProductCreateReqDto> products;
    private String addressUuid;
}
