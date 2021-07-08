package az.crbn.kanzopizza.ms.order.domain;

import az.crbn.kanzopizza.ms.order.domain.enums.OrderStatus;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Order.TABLE_NAME)
@EqualsAndHashCode(callSuper = true)
public class Order extends AbstractAuditingEntity {
    public static final String TABLE_NAME = "orders";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String uuid;

    @Size(min = 1)
    @JoinColumn(name = "order_id")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderProduct> products = new HashSet<>();

    @NotNull
    @Column(nullable = false)
    private String customerUuid;

    @NotNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private Instant lastModifiedDate;

    @NotNull
    @Column(nullable = false)
    private String addressUuid;

    @NotNull
    @Positive
    @Column(nullable = false)
    private BigDecimal totalAmount;

    @PrePersist
    public void prePersist() {
        setUuid(UUID.randomUUID().toString());
        setStatus(OrderStatus.IN_PROCESS);
    }
}
