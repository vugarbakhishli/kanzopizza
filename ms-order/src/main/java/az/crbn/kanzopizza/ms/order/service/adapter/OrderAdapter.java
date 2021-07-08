package az.crbn.kanzopizza.ms.order.service.adapter;

import az.crbn.kanzopizza.ms.order.domain.Order;
import az.crbn.kanzopizza.ms.order.service.dto.OrderResDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderAdapter {
    OrderResDto map(Order order);
}
