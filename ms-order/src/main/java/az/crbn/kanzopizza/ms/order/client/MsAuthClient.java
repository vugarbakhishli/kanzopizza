package az.crbn.kanzopizza.ms.order.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "${client.ms-auth.name}", url = "${client.ms-auth.host}${client.ms-auth.path}")
public interface MsAuthClient {
}
