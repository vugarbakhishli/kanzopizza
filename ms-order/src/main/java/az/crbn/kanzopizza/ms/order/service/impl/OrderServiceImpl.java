package az.crbn.kanzopizza.ms.order.service.impl;

import static az.crbn.kanzopizza.ms.order.domain.enums.OrderStatus.CANCELLED;
import static az.crbn.kanzopizza.ms.order.domain.enums.OrderStatus.FINISHED;
import static az.crbn.kanzopizza.ms.order.domain.enums.OrderStatus.IN_PROCESS;

import az.crbn.common.dto.customer.PayReqDto;
import az.crbn.common.dto.customer.RollbackReqDto;
import az.crbn.common.dto.product.ProductPriceResDto;
import az.crbn.common.security.auth.service.SecurityService;
import az.crbn.common.security.exception.UserNotFoundException;
import az.crbn.kanzopizza.ms.order.client.MsCustomerClient;
import az.crbn.kanzopizza.ms.order.client.MsProductClient;
import az.crbn.kanzopizza.ms.order.domain.Order;
import az.crbn.kanzopizza.ms.order.domain.OrderProduct;
import az.crbn.kanzopizza.ms.order.exception.OrderNotFoundException;
import az.crbn.kanzopizza.ms.order.exception.OrderStatusException;
import az.crbn.kanzopizza.ms.order.repository.OrderRepository;
import az.crbn.kanzopizza.ms.order.service.OrderService;
import az.crbn.kanzopizza.ms.order.service.adapter.OrderAdapter;
import az.crbn.kanzopizza.ms.order.service.dto.OrderCreateReqDto;
import az.crbn.kanzopizza.ms.order.service.dto.OrderProductCreateReqDto;
import az.crbn.kanzopizza.ms.order.service.dto.OrderResDto;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderAdapter orderAdapter;
    private final SecurityService securityService;
    private final MsProductClient msProductClient;
    private final MsCustomerClient msCustomerClient;

    @Override
    @Transactional
    public String create(OrderCreateReqDto reqDto) {
        var currentUserUuid = securityService.getCurrentUserUuid().orElseThrow(UserNotFoundException::new);

        var orderBuilder = Order
                .builder()
                .customerUuid(currentUserUuid)
                .addressUuid(reqDto.getAddressUuid());

        var prices = msProductClient.getProductPrice(
                reqDto
                        .getProducts()
                        .stream()
                        .map(OrderProductCreateReqDto::getProductUuid)
                        .collect(Collectors.toSet())
        );
        var summ = prices.stream().map(ProductPriceResDto::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);

        orderBuilder.products(
                prices
                        .stream()
                        .map(priceDto -> OrderProduct
                                .builder()
                                .productUuid(priceDto.getUuid())
                                .count(
                                        reqDto
                                                .getProducts()
                                                .stream()
                                                .filter(product -> product.getProductUuid().equals(priceDto.getUuid()))
                                                .findFirst()
                                                .map(OrderProductCreateReqDto::getCount)
                                                .orElse(0)
                                )
                                .build())
                        .collect(Collectors.toSet())
        );

        orderBuilder.totalAmount(summ);

        msCustomerClient.pay(new PayReqDto(summ));

        var saved = orderRepository.save(orderBuilder.build());
        return saved.getUuid();
    }

    @Override
    @Transactional
    public Page<OrderResDto> searchForAdmin(Pageable pageable) {
        return orderRepository
                .findAll(pageable)
                .map(orderAdapter::map);
    }

    @Override
    @Transactional
    public Page<OrderResDto> search(Pageable pageable) {
        var currentUserUuid = securityService.getCurrentUserUuid().orElseThrow(UserNotFoundException::new);

        return orderRepository
                .findAllByCustomerUuid(currentUserUuid, pageable)
                .map(orderAdapter::map);
    }

    @Override
    @Transactional
    public void cancel(String uuid) {
        var currentUserUuid = securityService.getCurrentUserUuid().orElseThrow(UserNotFoundException::new);
        var order = orderRepository
                .findByUuidAndCustomerUuid(uuid, currentUserUuid)
                .orElseThrow(() -> new OrderNotFoundException(uuid));

        if (!IN_PROCESS.equals(order.getStatus())) {
            throw new OrderStatusException(CANCELLED);
        }

        msCustomerClient.rollback(new RollbackReqDto(order.getTotalAmount()));
        orderRepository.updateOrderStatus(uuid, CANCELLED, Instant.now());
    }

    @Override
    @Transactional
    public void finish(String uuid) {
        var currentUserUuid = securityService.getCurrentUserUuid().orElseThrow(UserNotFoundException::new);
        var order = orderRepository
                .findByUuidAndCustomerUuid(uuid, currentUserUuid)
                .orElseThrow(() -> new OrderNotFoundException(uuid));

        if (!IN_PROCESS.equals(order.getStatus())) {
            throw new OrderStatusException(FINISHED);
        }

        orderRepository.updateOrderStatus(uuid, FINISHED, Instant.now());
    }
}
