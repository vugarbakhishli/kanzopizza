package az.crbn.kanzopizza.ms.order.service;

import az.crbn.kanzopizza.ms.order.service.dto.OrderCreateReqDto;
import az.crbn.kanzopizza.ms.order.service.dto.OrderResDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {
    String create(OrderCreateReqDto reqDto);

    Page<OrderResDto> searchForAdmin(Pageable pageable);

    Page<OrderResDto> search(Pageable pageable);

    void cancel(String uuid);

    void finish(String uuid);
}
