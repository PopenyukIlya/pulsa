package pulse.domain.quiz;

import javax.persistence.*;

@Entity
public class UserAnswers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Answer answer;
}
