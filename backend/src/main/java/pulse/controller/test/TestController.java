package pulse.controller.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import pulse.controller.dto.test.TestQuestion;
import pulse.controller.dto.TestResultDto;
import pulse.controller.dto.UserAnswerDto;
import pulse.domain.User;
import pulse.service.test.TestService;

@CrossOrigin(origins = "https://pacific-fjord-28473.herokuapp.com", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("/{id}")
    public TestQuestion start(@PathVariable(value = "id") Long id, @AuthenticationPrincipal User user) {
        return testService.start(id,user);
    }

    @GetMapping
    public TestQuestion check(@AuthenticationPrincipal User user) {
        return testService.check(user);
    }

    @PostMapping
    public TestQuestion checkAnswerGetQuestion(@RequestBody UserAnswerDto testAnswer) {
        return testService.getQuestion(testAnswer);
    }

    @GetMapping("/result/{id}")
    public TestResultDto result(@PathVariable(value = "id") Long id) {
        return testService.result(id);
    }



}
