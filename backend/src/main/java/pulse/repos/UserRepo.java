package pulse.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import pulse.domain.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
