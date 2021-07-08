package az.crbn.kanzopizza.ms.product.service.impl;

import az.crbn.common.dto.product.ProductPriceResDto;
import az.crbn.kanzopizza.ms.product.exception.ProductNotFoundException;
import az.crbn.kanzopizza.ms.product.repository.ProductRepository;
import az.crbn.kanzopizza.ms.product.service.ProductService;
import az.crbn.kanzopizza.ms.product.service.adapter.ProductAdapter;
import az.crbn.kanzopizza.ms.product.service.dto.ProductCreateReqDto;
import az.crbn.kanzopizza.ms.product.service.dto.ProductResDto;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductAdapter productAdapter;

    @Override
    public ProductResDto create(ProductCreateReqDto productCreateReqDto) {
        var product = productAdapter.map(productCreateReqDto);
        return productAdapter.map(productRepository.save(product));
    }

    @Override
    public Page<ProductResDto> search(Pageable pageable) {
        return productRepository
                .findAll(pageable)
                .map(productAdapter::map);
    }

    @Override
    public Set<ProductPriceResDto> getProductPrice(Set<String> productUuids) {
        return productRepository
                .findAllByUuidIn(new ArrayList<>(productUuids))
                .stream()
                .map(product -> new ProductPriceResDto(product.getUuid(), product.getPrice()))
                .collect(Collectors.toSet());
    }

    @Override
    public ProductResDto getById(String uuid) {
        return productRepository
                .findByUuid(uuid)
                .map(productAdapter::map)
                .orElseThrow(() -> new ProductNotFoundException(uuid));
    }

    @Override
    public void delete(String uuid) {
        var product = productRepository.findByUuid(uuid).orElseThrow(() -> new ProductNotFoundException(uuid));
        productRepository.delete(product);
    }
}
