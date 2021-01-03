package pulse.controller.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pulse.controller.dto.AllQuizDto;
import pulse.controller.dto.QuizDto;
import pulse.domain.quiz.Quiz;
import pulse.domain.repos.QuizRepo;
import pulse.service.admin.EditDeleteQuizService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/quiz")
public class QuizCreateEditDeleteController {

    @Autowired
    private EditDeleteQuizService editDeleteQuizService;

    @Autowired
    private QuizRepo quizRepo;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<QuizDto> findAll() {
        return editDeleteQuizService.findAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public AllQuizDto findByID(@PathVariable("id") Long id) {
        return editDeleteQuizService.findByID(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody AllQuizDto quiz){
        return editDeleteQuizService.create(quiz);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        return editDeleteQuizService.delete(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestBody AllQuizDto quiz){
        return editDeleteQuizService.update(quiz);
    }

}
