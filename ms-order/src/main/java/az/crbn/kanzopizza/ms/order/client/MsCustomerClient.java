package az.crbn.kanzopizza.ms.order.client;

import az.crbn.common.dto.customer.PayReqDto;
import az.crbn.common.dto.customer.RollbackReqDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "${client.ms-customer.name}", url = "${client.ms-customer.host}${client.ms-customer.path}")
public interface MsCustomerClient {

    @PostMapping("/api/payment/v1/pay")
    void pay(@RequestBody PayReqDto reqDto);

    @PostMapping("/api/payment/v1/rollback")
    void rollback(@RequestBody RollbackReqDto reqDto);
}
