package pulse.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestAnswer {
    private Long id;
    private Long progress;
    private String text;
    private Long questionId;
}
