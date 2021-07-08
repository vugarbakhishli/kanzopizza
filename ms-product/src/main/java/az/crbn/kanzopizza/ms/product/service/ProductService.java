package az.crbn.kanzopizza.ms.product.service;

import az.crbn.common.dto.product.ProductPriceResDto;
import az.crbn.kanzopizza.ms.product.service.dto.ProductCreateReqDto;
import az.crbn.kanzopizza.ms.product.service.dto.ProductResDto;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    ProductResDto create(ProductCreateReqDto productCreateReqDto);

    Page<ProductResDto> search(Pageable pageable);

    Set<ProductPriceResDto> getProductPrice(Set<String> productUuids);

    ProductResDto getById(String uuid);

    void delete(String uuid);
}
