package pro.sing.demojavacorcoursework;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pro.sing.demojavacorcoursework.model.Question;
import pro.sing.demojavacorcoursework.service.serviceimple.JavaQuestionService;

import java.util.List;
import java.util.Random;

import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
public class JavaQuestionServiceTest {
    private JavaQuestionService javaQuestionService;

    @Mock
    private Random random;

    @InjectMocks
    private JavaQuestionService javaQuestionServiceMock;


    @BeforeEach
    public void setUp() {
        javaQuestionService = new JavaQuestionService();
    }

    @Test
    public void testAddQuestion() {
        Question question = new Question("Как дела?", "Норм");
        javaQuestionService.addQuestion(question);
        List<Question> res = javaQuestionService.getQuestions();

        Assertions.assertEquals(1, res.size());
    }

    @Test
    public void testRemoveQuestion() {
        Question question1 = new Question("Как дела?", "Норм");
        Question question2 = new Question("Что делаешь?", "Ни чего");
        javaQuestionService.addQuestion(question1);
        javaQuestionService.addQuestion(question2);
        javaQuestionService.removeQuestion(question1);
        List<Question> res = javaQuestionService.getQuestions();

        Assertions.assertEquals(1, res.size());
    }

    @Test
    public void testGetQuestion() {
        Question question1 = new Question("Как дела?", "Норм");
        Question question2 = new Question("Что делаешь?", "Ни чего");
        javaQuestionService.addQuestion(question1);
        javaQuestionService.addQuestion(question2);
        Question res = javaQuestionService.getQuestion(1);
        Assertions.assertEquals(question2, res);
    }

    @Test
    public void testRandomQuestion() {
        Question question1 = new Question("Как дела?", "Норм");
        Question question2 = new Question("Что делаешь?", "Ни чего");
        javaQuestionServiceMock.addQuestion(question1);
        javaQuestionServiceMock.addQuestion(question2);

        when(random.nextInt(javaQuestionServiceMock.getQuestions().size())).thenReturn(1);

        Question res = javaQuestionServiceMock.getRandomQuestion();

        Assertions.assertEquals(question2, res);


    }

}
