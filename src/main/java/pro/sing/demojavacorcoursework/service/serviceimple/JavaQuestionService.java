package pro.sing.demojavacorcoursework.service.serviceimple;

import pro.sing.demojavacorcoursework.model.Question;
import pro.sing.demojavacorcoursework.service.QuestionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JavaQuestionService implements QuestionService {

    private final List<Question> questions = new ArrayList<>();
    Random random = new Random();

    @Override
    public Question getRandomQuestion() {
        int randomNumber = random.nextInt(questions.size());
        return getQuestion(randomNumber);
    }

    @Override
    public List<Question> getQuestions() {
        return questions;
    }

    @Override
    public void addQuestion(Question question) {
        questions.add(question);
    }

    @Override
    public void removeQuestion(Question question) {
        questions.remove(question);
    }

    @Override
    public Question getQuestion(int index) {
        return questions.get(index);
    }
}
