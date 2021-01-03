package pulse.domain.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import pulse.domain.quiz.Answer;
import pulse.domain.quiz.Pulse;

public interface AnswerRepo extends JpaRepository<Answer,Long> {
}
