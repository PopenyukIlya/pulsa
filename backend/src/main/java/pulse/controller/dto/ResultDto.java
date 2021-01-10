package pulse.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultDto {
    private Long id;
    private QuizDto quiz;
    private int grade;
    private String endData;
}
