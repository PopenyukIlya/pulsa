package pulse.domain.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import pulse.domain.User;
import pulse.domain.quiz.QuizProgress;

import java.util.List;

public interface QuizProgressRepo extends JpaRepository<QuizProgress,Long> {
    List<QuizProgress> findByUser(User user);
}
