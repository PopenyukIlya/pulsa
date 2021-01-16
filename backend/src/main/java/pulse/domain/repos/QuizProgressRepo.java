package pulse.domain.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pulse.domain.User;
import pulse.domain.quiz.Quiz;
import pulse.domain.quiz.QuizProgress;

import java.util.List;

@Repository
public interface QuizProgressRepo extends JpaRepository<QuizProgress,Long> {
    List<QuizProgress> findByUser(User user);

    List<QuizProgress> findByQuiz(Quiz quiz);
}
