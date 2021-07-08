package az.crbn.kanzopizza.ms.order.service.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductCreateReqDto {
    @NotNull
    private String productUuid;

    @NotNull
    @Positive
    private Integer count;
}
