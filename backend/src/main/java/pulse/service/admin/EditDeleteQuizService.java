package pulse.service.admin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pulse.domain.Quiz;
import pulse.domain.User;
import pulse.repos.QuizRepo;

import java.util.List;

@Service
public class EditDeleteQuizService {

    @Autowired
    private QuizRepo quizRepo;

    public ResponseEntity<?> findAll() {
        List<Quiz> quizzes=quizRepo.findAll();
            return new ResponseEntity<>(quizzes,HttpStatus.OK);

    }

    public ResponseEntity<?> create(Quiz quiz) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> delete(Long id) {
        return new ResponseEntity<>(HttpStatus.OK);

    }

    public ResponseEntity<?> update(Long id, Quiz quiz) {
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
