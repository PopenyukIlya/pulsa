package pulse.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pulse.controller.dto.ResultByQuizDto;
import pulse.controller.dto.ResultDto;
import pulse.domain.User;
import pulse.domain.quiz.Quiz;
import pulse.domain.repos.QuizProgressRepo;
import pulse.domain.repos.QuizRepo;
import pulse.service.admin.EditDeleteQuizService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultService {
    @Autowired
    private QuizProgressRepo quizProgressRepo;
    @Autowired
    private QuizRepo quizRepo;
    @Autowired
    private EditDeleteQuizService editDeleteQuizService;
    @Autowired
    private ModelMapper modelMapper;

    public List<ResultDto> results(User user) {
       return quizProgressRepo.findByUser(user).stream().map(quizProgress -> modelMapper.map(quizProgress,ResultDto.class)).collect(Collectors.toList());
    }

    public List<ResultByQuizDto> resultByTest(Long id) {
        Quiz quiz=quizRepo.findById(id).get();
        return quizProgressRepo.findByQuiz(quiz).stream().map(quizProgress -> modelMapper.map(quizProgress,ResultByQuizDto.class)).collect(Collectors.toList());
    }
}
