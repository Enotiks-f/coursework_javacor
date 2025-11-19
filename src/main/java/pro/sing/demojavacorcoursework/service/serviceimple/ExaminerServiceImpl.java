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
    public List<String> getQuestions(int question) {

        if (question > javaQuestionService.getQuestions().size()) {
            throw new IllegalArgumentException();
        }

        List<String> questions = new ArrayList<>();
        for (int i = 0; i < question; i++) {
            String que = javaQuestionService.getRandomQuestion().getQuestion();
            questions.add(que);
        }
        return questions;
    }
}
