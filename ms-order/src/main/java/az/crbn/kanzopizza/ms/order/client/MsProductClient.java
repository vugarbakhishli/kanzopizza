package az.crbn.kanzopizza.ms.order.client;

import az.crbn.common.dto.product.ProductPriceResDto;
import java.util.Set;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "${client.ms-product.name}", url = "${client.ms-product.host}${client.ms-product.path}")
public interface MsProductClient {

    @PostMapping("/api/products/v1/price")
    Set<ProductPriceResDto> getProductPrice(@RequestBody Set<String> productUuids);
}

