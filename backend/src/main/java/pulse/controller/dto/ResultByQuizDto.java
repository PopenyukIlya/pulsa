package pulse.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultByQuizDto {
    private Long id;
    private UserDto user;
    private int grade;
}
