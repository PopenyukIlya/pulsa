package pulse.domain.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import pulse.domain.quiz.Question;

public interface QuestionRepo extends JpaRepository<Question,Long> {
}
