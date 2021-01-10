package pulse.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pulse.controller.dto.ResultDto;
import pulse.domain.User;
import pulse.domain.repos.QuizProgressRepo;
import pulse.service.admin.EditDeleteQuizService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultService {
    @Autowired
    private QuizProgressRepo quizProgressRepo;
    @Autowired
    private EditDeleteQuizService editDeleteQuizService;
    @Autowired
    private ModelMapper modelMapper;

    public List<ResultDto> results(User user) {
       return quizProgressRepo.findByUser(user).stream().map(quizProgress -> modelMapper.map(quizProgress,ResultDto.class)).collect(Collectors.toList());
    }
}
