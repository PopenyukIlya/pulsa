package pulse.domain.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import pulse.domain.quiz.Pulse;

public interface PulseRepo extends JpaRepository<Pulse,Long> {
}
