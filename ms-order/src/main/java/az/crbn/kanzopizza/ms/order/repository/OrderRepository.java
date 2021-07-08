package az.crbn.kanzopizza.ms.order.repository;

import az.crbn.kanzopizza.ms.order.domain.Order;
import az.crbn.kanzopizza.ms.order.domain.enums.OrderStatus;
import java.time.Instant;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findAllByCustomerUuid(String customerUuid, Pageable pageable);

    Optional<Order> findByCustomerUuid(String customerUuid);

    Optional<Order> findByUuidAndCustomerUuid(String uuid, String customerUuid);

    @Modifying
    @Query("UPDATE Order o SET o.status=:status, o.lastModifiedDate=:lastModifiedDate WHERE o.uuid=:uuid")
    void updateOrderStatus(@Param("uuid") String uuid,
                           @Param("status") OrderStatus status,
                           @Param("lastModifiedDate") Instant lastModifiedDate);

}
