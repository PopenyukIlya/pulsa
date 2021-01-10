package pulse.controller.dto;


import lombok.Getter;
import lombok.Setter;
import pulse.controller.dto.test.ListAnswer;

import java.util.List;

@Getter
@Setter
public class UserAnswerDto {
    private Long progress;
    private Long id;
    private List<ListAnswer> answers;
}
