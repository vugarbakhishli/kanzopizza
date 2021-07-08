package az.crbn.kanzopizza.ms.customer.domain;

import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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
@Table(name = Customer.TABLE_NAME)
@EqualsAndHashCode(callSuper = true)
public class Customer extends AbstractAuditingEntity {
    public static final String TABLE_NAME = "customers";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String uuid;

    @NotNull
    @Column(nullable = false, unique = true)
    private String userUuid;

    @Min(0)
    @NotNull
    @Column(nullable = false)
    private BigDecimal balance;

    @PrePersist
    public void prePersist() {
        setUuid(UUID.randomUUID().toString());
        setBalance(BigDecimal.valueOf(0));
    }
}
