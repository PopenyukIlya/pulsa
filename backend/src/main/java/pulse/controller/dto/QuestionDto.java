package pulse.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class QuestionDto {
    private Long id;
    private String text;
    private int complexity;
    private List<AnswerDto> answers;

}
