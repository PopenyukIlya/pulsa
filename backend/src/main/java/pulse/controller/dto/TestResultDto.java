package pulse.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TestResultDto {
    private AllQuizDto allQuizDto;
    private List<AnswerDto> userAnswers;
}
