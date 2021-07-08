package az.crbn.kanzopizza.ms.customer.web.rest;

import static org.springframework.http.HttpStatus.CREATED;

import az.crbn.common.dto.customer.RegisterReqDto;
import az.crbn.kanzopizza.ms.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/v1/register")
    public ResponseEntity<Void> register(@RequestBody @Validated RegisterReqDto reqDto) {
        customerService.register(reqDto.getUserUuid());
        return ResponseEntity.status(CREATED).build();
    }
}
