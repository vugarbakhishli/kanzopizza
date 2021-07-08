package az.crbn.kanzopizza.ms.order.domain;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = OrderProduct.TABLE_NAME)
public class OrderProduct {
    public static final String TABLE_NAME = "order_products";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String uuid;

    @NotNull
    @Column(nullable = false)
    private String productUuid;

    @NotNull
    @Positive
    @Column(nullable = false)
    private Integer count;

    @PrePersist
    public void prePersist() {
        setUuid(UUID.randomUUID().toString());
    }
}
