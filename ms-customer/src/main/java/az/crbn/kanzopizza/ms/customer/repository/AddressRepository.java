package az.crbn.kanzopizza.ms.customer.repository;

import az.crbn.kanzopizza.ms.customer.domain.Address;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findByUuid(String uuid);
}
