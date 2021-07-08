package az.crbn.kanzopizza.ms.customer.repository;

import az.crbn.kanzopizza.ms.customer.domain.Customer;
import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUserUuid(String uuid);

    @Modifying
    @Query("UPDATE Customer c SET c.balance=:totalAmount WHERE c.id=:id")
    void updateBalance(@Param("id") Long id, @Param("totalAmount") BigDecimal totalAmount);
}
