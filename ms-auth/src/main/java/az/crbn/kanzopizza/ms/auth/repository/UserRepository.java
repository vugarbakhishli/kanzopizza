package az.crbn.kanzopizza.ms.auth.repository;

import az.crbn.kanzopizza.ms.auth.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}
