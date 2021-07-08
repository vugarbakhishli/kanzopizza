package az.crbn.kanzopizza.ms.product.repository;

import az.crbn.kanzopizza.ms.product.domain.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByUuid(String uuid);

    List<Product> findAllByUuidIn(List<String> productUuids);
}
