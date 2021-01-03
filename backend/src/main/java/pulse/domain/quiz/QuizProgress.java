package pulse.domain.quiz;

import lombok.Data;
import pulse.domain.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class QuizProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Quiz quiz;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Answer> answers;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Question> passedQuestions;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    private QuizStatus status;
}
