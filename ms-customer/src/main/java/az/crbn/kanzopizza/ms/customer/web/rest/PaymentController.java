package az.crbn.kanzopizza.ms.customer.web.rest;

import az.crbn.common.dto.customer.PayReqDto;
import az.crbn.common.dto.customer.RollbackReqDto;
import az.crbn.kanzopizza.ms.customer.service.PaymentService;
import az.crbn.kanzopizza.ms.customer.service.dto.TopReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/v1/pay")
    public ResponseEntity<Void> pay(@RequestBody @Validated PayReqDto reqDto) {
        paymentService.pay(reqDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/v1/rollback")
    public ResponseEntity<Void> rollback(@RequestBody @Validated RollbackReqDto reqDto) {
        paymentService.rollback(reqDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/v1/top")
    public ResponseEntity<Void> top(@RequestBody @Validated TopReqDto topReqDto) {
        paymentService.top(topReqDto);
        return ResponseEntity.ok().build();
    }
}
