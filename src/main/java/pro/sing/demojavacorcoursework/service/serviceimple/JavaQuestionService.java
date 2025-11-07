package pro.sing.demojavacorcoursework.service.serviceimple;

import org.springframework.stereotype.Service;
import pro.sing.demojavacorcoursework.exception.QuestionAlreadyAddedException;
import pro.sing.demojavacorcoursework.exception.QuestionNotFoundException;
import pro.sing.demojavacorcoursework.model.Question;
import pro.sing.demojavacorcoursework.service.QuestionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class JavaQuestionService implements QuestionService {

    private final List<Question> questions = new ArrayList<>();
    Random random = new Random();

    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            throw new IllegalStateException("Список вопросов пуст");
        }
        int randomNumber = random.nextInt(questions.size());
        return getQuestion(randomNumber);
    }

    @Override
    public List<Question> getQuestions() {
        return new ArrayList<>(questions);
    }

    @Override
    public void addQuestion(String question, String answer) {
        if (questions.contains(new Question(question, answer))) {
            throw new QuestionAlreadyAddedException("Вопрос уже существует");
        }
        questions.add(new Question(question, answer));
    }

    @Override
    public void removeQuestion(String question, String answer) {

        if (!questions.contains(new Question(question, answer))) {
            throw new QuestionNotFoundException("Вопрос не найден");
        }

        questions.remove(new Question(question, answer));
    }

    @Override
    public Question getQuestion(int index) {
        if (index < 0 || index >= questions.size()) {
            throw new IndexOutOfBoundsException("Неверный индекс: " + index);
        }

        return questions.get(index);
    }
}
