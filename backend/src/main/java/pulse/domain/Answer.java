package pulse.domain;

import javax.persistence.*;


@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;
    private boolean correct;
    @ManyToOne
    private Question question;

}
