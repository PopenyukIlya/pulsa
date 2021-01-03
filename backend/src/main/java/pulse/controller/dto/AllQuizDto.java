package pulse.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AllQuizDto extends QuizDto{
    List<QuestionDto> questions;

}
