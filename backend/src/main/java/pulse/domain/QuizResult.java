package pulse.domain;

import javax.persistence.*;

@Entity
public class QuizResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Quiz quiz;

}
