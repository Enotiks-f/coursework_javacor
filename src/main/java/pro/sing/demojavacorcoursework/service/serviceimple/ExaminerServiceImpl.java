package pro.sing.demojavacorcoursework.service.serviceimple;

import org.springframework.stereotype.Service;
import pro.sing.demojavacorcoursework.model.Question;
import pro.sing.demojavacorcoursework.service.ExaminerService;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final List<Question> questions = new ArrayList<>();

    private final JavaQuestionService javaQuestionService;

    public ExaminerServiceImpl(JavaQuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @Override
    public List<Question> getQuestions(int question) {
        List<Question> questionsList = javaQuestionService.getQuestions();
        while (question > 0) {
            questions.add(javaQuestionService.getRandomQuestion());
        }
        question--;
        return questions;
    }
}
