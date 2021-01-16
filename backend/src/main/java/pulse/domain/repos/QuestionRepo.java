package pulse.domain.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pulse.domain.quiz.Answer;
import pulse.domain.quiz.Question;

@Repository
public interface QuestionRepo extends JpaRepository<Question,Long> {
  //  Question findByAnswer(Answer qAnswer);
}
