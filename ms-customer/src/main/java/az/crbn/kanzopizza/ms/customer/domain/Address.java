package az.crbn.kanzopizza.ms.customer.domain;

import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Address.TABLE_NAME)
@EqualsAndHashCode(callSuper = true)
public class Address extends AbstractAuditingEntity {
    public static final String TABLE_NAME = "addresses";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String uuid;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @JoinColumn(name = "customer_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @NotNull
    @Column(nullable = false)
    private BigDecimal latitude;

    @NotNull
    @Column(nullable = false)
    private BigDecimal longitude;

    @PrePersist
    public void prePersist() {
        setUuid(UUID.randomUUID().toString());
    }
}
