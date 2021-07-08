package az.crbn.kanzopizza.ms.auth.client;

import az.crbn.common.dto.customer.RegisterReqDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "${client.ms-customer.name}", url = "${client.ms-customer.host}${client.ms-customer.path}")
public interface MsCustomerClient {

    @PostMapping("/api/customers/v1/register")
    void register(@RequestBody RegisterReqDto reqDto);
}
