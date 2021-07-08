package az.crbn.kanzopizza.ms.product.web.rest;

import static org.springframework.http.HttpStatus.CREATED;

import az.crbn.common.dto.product.ProductPriceResDto;
import az.crbn.kanzopizza.ms.product.service.ProductService;
import az.crbn.kanzopizza.ms.product.service.dto.ProductCreateReqDto;
import az.crbn.kanzopizza.ms.product.service.dto.ProductResDto;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/v1")
    public ResponseEntity<ProductResDto> create(@RequestBody @Validated ProductCreateReqDto reqDto) {
        return ResponseEntity.status(CREATED).body(productService.create(reqDto));
    }

    @GetMapping("/v1")
    public ResponseEntity<Page<ProductResDto>> search(Pageable pageable) {
        // TODO: add search criteria
        return ResponseEntity.ok(productService.search(pageable));
    }

    @PostMapping("/v1/price")
    public ResponseEntity<Set<ProductPriceResDto>> getProductPrice(@RequestBody Set<String> productUuids) {
        return ResponseEntity.ok(productService.getProductPrice(productUuids));
    }

    @GetMapping("/v1/{uuid}")
    public ResponseEntity<ProductResDto> getById(@PathVariable String uuid) {
        return ResponseEntity.ok(productService.getById(uuid));
    }

    @DeleteMapping("/v1/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable String uuid) {
        productService.delete(uuid);
        return ResponseEntity.noContent().build();
    }
}
