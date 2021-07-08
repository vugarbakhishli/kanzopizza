package az.crbn.kanzopizza.ms.product.service.adapter;

import az.crbn.kanzopizza.ms.product.domain.Product;
import az.crbn.kanzopizza.ms.product.service.dto.ProductCreateReqDto;
import az.crbn.kanzopizza.ms.product.service.dto.ProductResDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductAdapter {
    @Mapping(target = "uuid", ignore = true)
    @Mapping(target = "id", ignore = true)
    Product map(ProductCreateReqDto productCreateReqDto);

    ProductResDto map(Product product);
}
