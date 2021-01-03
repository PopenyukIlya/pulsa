package pulse.domain.quiz;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@RequiredArgsConstructor
public class Pulse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int pulse;
}
