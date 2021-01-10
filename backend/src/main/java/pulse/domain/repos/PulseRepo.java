package pulse.domain.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pulse.domain.quiz.Pulse;

@Repository
public interface PulseRepo extends JpaRepository<Pulse,Long> {
}
