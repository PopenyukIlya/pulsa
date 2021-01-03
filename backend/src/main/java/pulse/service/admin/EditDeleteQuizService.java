package pulse.service.admin;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pulse.controller.dto.AllQuizDto;
import pulse.controller.dto.QuizDto;
import pulse.domain.quiz.Quiz;
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
    private QuizRepo quizRepo;


    public List<QuizDto> findAll() {
       return quizRepo.findAll().stream()
                .map(quiz -> modelMapper.map(quiz, QuizDto.class)).collect(Collectors.toList());
    }

    public ResponseEntity<?> create(AllQuizDto quizDto) {
        Quiz quiz=modelMapper.map(quizDto,Quiz.class);
        quizRepo.save(quiz);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> delete(Long id) {
        quizRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> update(AllQuizDto quizDto) {
       Quiz old=quizRepo.findById(quizDto.getId()).get();
        Quiz update=modelMapper.map(quizDto,Quiz.class);
        old.getQuestions().clear();
        old.getQuestions().addAll(update.getQuestions());
        old.setName(update.getName());
        quizRepo.save(old);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    public AllQuizDto findByID(Long id) {
        return  modelMapper.map(quizRepo.findById(id).get(), AllQuizDto.class);
    }
}
