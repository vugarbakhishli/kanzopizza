package az.crbn.kanzopizza.ms.order.web.rest;

import static org.springframework.http.HttpStatus.CREATED;

import az.crbn.kanzopizza.ms.order.service.OrderService;
import az.crbn.kanzopizza.ms.order.service.dto.OrderCreateReqDto;
import az.crbn.kanzopizza.ms.order.service.dto.OrderResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/v1")
    public ResponseEntity<String> create(@RequestBody @Validated OrderCreateReqDto reqDto) {
        return ResponseEntity.status(CREATED).body(orderService.create(reqDto));
    }

    @GetMapping("/v1/admin")
    public ResponseEntity<Page<OrderResDto>> searchForAdmin(Pageable pageable) {
        // TODO: add search criteria
        return ResponseEntity.ok(orderService.searchForAdmin(pageable));
    }

    @GetMapping("/v1")
    public ResponseEntity<Page<OrderResDto>> search(Pageable pageable) {
        // TODO: add search criteria
        return ResponseEntity.ok(orderService.search(pageable));
    }

    @PostMapping("/v1/{uuid}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable String uuid) {
        orderService.cancel(uuid);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/v1/{uuid}/finish")
    public ResponseEntity<Void> finish(@PathVariable String uuid) {
        orderService.finish(uuid);
        return ResponseEntity.ok().build();
    }
}
