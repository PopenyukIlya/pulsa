package pulse.domain.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pulse.domain.quiz.Answer;
import pulse.domain.quiz.Pulse;

@Repository
public interface AnswerRepo extends JpaRepository<Answer,Long> {
}
