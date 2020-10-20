package pulse.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;
    @OneToMany
    private Set<Answer> answers;
    @ManyToOne
    private Quiz quiz;

}
