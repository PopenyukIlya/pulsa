package pulse.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pulse.domain.Quiz;

@Repository
public interface QuizRepo extends JpaRepository<Quiz,Long> {
}
