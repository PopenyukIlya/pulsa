package pulse.service.admin;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pulse.controller.dto.AllQuizDto;
import pulse.controller.dto.QuizDto;
import pulse.domain.quiz.Question;
import pulse.domain.quiz.Quiz;
import pulse.domain.repos.QuestionRepo;
import pulse.domain.repos.QuizRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EditDeleteQuizService {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        return mapper;
    }

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private QuestionRepo questionRepo;
    @Autowired
    private QuizRepo quizRepo;


    public List<QuizDto> findAll() {
       return quizRepo.findAll().stream()
                .map(quiz -> modelMapper.map(quiz, QuizDto.class)).collect(Collectors.toList());
    }

    public ResponseEntity<?> create(String name) {
        Quiz quiz=new Quiz();
        quiz.setName(name);
        quizRepo.save(quiz);
        Long id=quiz.getId();
        return new ResponseEntity<>(id,HttpStatus.OK);
    }

    public ResponseEntity<?> delete(Long id) {
        quizRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> update(AllQuizDto quizDto) {
       Quiz old=quizRepo.findById(quizDto.getId()).get();
        Quiz update=modelMapper.map(quizDto,Quiz.class);
        List <Question> questions=old.getQuestions();
        quizRepo.save(update);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    public AllQuizDto findByID(Long id) {
        return  modelMapper.map(quizRepo.findById(id).get(), AllQuizDto.class);
    }
}
