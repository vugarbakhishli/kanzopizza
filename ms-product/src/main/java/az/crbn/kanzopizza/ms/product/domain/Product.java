package az.crbn.kanzopizza.ms.product.domain;

import java.math.BigDecimal;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Product.TABLE_NAME)
public class Product {
    public static final String TABLE_NAME = "products";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String uuid;

    @NotBlank
    @Column(nullable = false)
    private String title;
    private String description;

    @NotNull
    @Positive
    @Column(nullable = false)
    private BigDecimal price;

    @PrePersist
    public void prePersist() {
        setUuid(UUID.randomUUID().toString());
    }
}
