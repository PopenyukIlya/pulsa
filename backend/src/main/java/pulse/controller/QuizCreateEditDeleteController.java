package pulse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pulse.domain.Quiz;
import pulse.domain.User;
import pulse.service.admin.EditDeleteQuizService;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/api/admin/quizedit")
@PreAuthorize("hasAuthority('ADMIN')")
public class QuizCreateEditDeleteController {

    @Autowired
    private EditDeleteQuizService editDeleteQuizService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> findAll() {
        return editDeleteQuizService.findAll();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody @Valid Quiz quiz){
        return editDeleteQuizService.create(quiz);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        return editDeleteQuizService.delete(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable ("id") Long id,
                                    @RequestBody @Valid User user){
        return editDeleteQuizService.update(id,user);
    }

}
