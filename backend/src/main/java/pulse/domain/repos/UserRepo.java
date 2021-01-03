package pulse.domain.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import pulse.domain.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);
}
