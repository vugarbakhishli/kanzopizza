package az.crbn.kanzopizza.ms.auth.domain;

import static az.crbn.common.security.constants.AuthConstants.PASSWORD_MIN_LENGTH;

import az.crbn.common.security.constants.UserAuthority;
import az.crbn.common.security.constants.UserStatus;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = User.TABLE_NAME)
@ToString(exclude = "password")
@EqualsAndHashCode(callSuper = true)
public class User extends AbstractAuditingEntity {
    public static final String TABLE_NAME = "users";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String uuid;

    @NotBlank
    @Column(nullable = false)
    private String fullName;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Column(nullable = false)
    @Size(min = PASSWORD_MIN_LENGTH, max = 255)
    private String password;

    private UserAuthority authority;

    @NotNull
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @PrePersist
    public void prePersist() {
        setUuid(UUID.randomUUID().toString());
    }
}
