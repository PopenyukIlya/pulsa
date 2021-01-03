package pulse.service.test;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pulse.controller.dto.AllQuizDto;
import pulse.controller.dto.TestAnswer;
import pulse.controller.dto.TestQuestion;
import pulse.controller.dto.TestResultDto;
import pulse.domain.User;
import pulse.domain.quiz.*;
import pulse.domain.repos.AnswerRepo;
import pulse.domain.repos.QuizProgressRepo;
import pulse.domain.repos.QuizRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TestService {

    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AnswerRepo answerRepo;

    @Autowired
    private QuizProgressRepo quizProgressRepo;

    public TestQuestion start(Long id, User user) {
        Quiz quiz = quizRepo.findById(id).get();
        QuizProgress quizProgress = new QuizProgress();
        quizProgress.setQuiz(quiz);
        quizProgress.setUser(user);
        quizProgress.setStatus(QuizStatus.STARTED);
        quizProgress = quizProgressRepo.save(quizProgress);
        TestQuestion testQuestion = null;
        List<Question> questions = quiz.getQuestions();
        for (Question question : questions) {
            if (question.getComplexity() < 3) {
                testQuestion = modelMapper.map(question, TestQuestion.class);
            }
        }
        testQuestion.setProgress(quizProgress.getId());
        return testQuestion;
    }

    public TestQuestion check(User user) {
        List<QuizProgress> quizProgresses = quizProgressRepo.findByUser(user);
        TestQuestion testQuestion = null;
        Quiz quiz = null;
        for (QuizProgress quizProgress : quizProgresses) {
            if (quizProgress.getStatus().equals(QuizStatus.STARTED)) {
                quiz = quizProgress.getQuiz();
                List<Question> questions = quiz.getQuestions();
                for (Question question : questions) {
                    if (question.getComplexity() < 3) {
                        testQuestion = modelMapper.map(question, TestQuestion.class);
                        testQuestion.setProgress(quizProgress.getId());
                    }
                }
            }
        }
        return testQuestion;
    }

    public TestQuestion getQuestion(TestAnswer testAnswer) {
        // пришедшего ответа
        TestQuestion testQuestion = new TestQuestion();
        Quiz quiz = null;
        QuizProgress quizProgress = quizProgressRepo.findById(testAnswer.getProgress()).get();
        if (quizProgress.getStatus().equals(QuizStatus.STARTED)) {
            Answer answer = answerRepo.findById(testAnswer.getId()).get();
            if (answer.isCorrect()) {
                testQuestion.setTestStatus("true");
            } else {
                testQuestion.setTestStatus("false");
            }
            quizProgress.getAnswers().add(answer);
            quiz = quizProgress.getQuiz();
            List<Question> passed = quizProgress.getPassedQuestions();
            List<Question> questions = quiz.getQuestions();
            for (Question question : questions) {
                if (question.getComplexity() < 3 && !passed.contains(question)) {//тут нахождение сложности по пульсу
                    testQuestion = modelMapper.map(question, TestQuestion.class);
                    quizProgress.getPassedQuestions().add(question);
                }
            }
        }
        if (testQuestion.getAnswers().size() == 0) {
            testQuestion.getTestStatus().concat(" passed");
            quizProgress.setStatus(QuizStatus.ENDED);
        }
        testQuestion.setProgress(quizProgress.getId());
        quizProgressRepo.save(quizProgress);
        return testQuestion;
    }

    public TestResultDto result(Long id) {
        QuizProgress quizProgress = quizProgressRepo.findById(id).get();
        //взять вопросы
        Quiz quiz = quizProgress.getQuiz();
        AllQuizDto allQuizDto = modelMapper.map(quiz, AllQuizDto.class);
        List<UserAnswers> userAnswers = quizProgress.getAnswers()
                .stream().map(answer -> modelMapper.map(answer, UserAnswers.class)).collect(Collectors.toList());
        TestResultDto testResultDto = new TestResultDto();
        testResultDto.setAllQuizDto(allQuizDto);
        testResultDto.setUserAnswers(userAnswers);
        return null;
    }
}
