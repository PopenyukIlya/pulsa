package pulse.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TestQuestion {
    private Long id;
    private Long progress;
    private String text;
    private List<TestAnswer> answers;
    private String testStatus;
}
