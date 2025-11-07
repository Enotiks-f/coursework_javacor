package pro.sing.demojavacorcoursework;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pro.sing.demojavacorcoursework.exception.QuestionAlreadyAddedException;
import pro.sing.demojavacorcoursework.exception.QuestionNotFoundException;
import pro.sing.demojavacorcoursework.model.Question;
import pro.sing.demojavacorcoursework.service.serviceimple.JavaQuestionService;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
public class JavaQuestionServiceTest {

    @Mock
    private Random random;

    @InjectMocks
    private JavaQuestionService javaQuestionService;

    private Question question1;
    private Question question2;


    @BeforeEach
    public void setUp() {
        question1 = new Question("Что такое Java?", "Язык программирования");
        question2 = new Question("Что такое OOP?", "Объектно-ориентированное программирование");
    }

    @Test
    void addQuestion_ShouldAddQuestion() {
        javaQuestionService.addQuestion(question1.getQuestion(), question1.getAnswer());

        assertEquals(1, javaQuestionService.getQuestions().size());
        assertTrue(javaQuestionService.getQuestions().contains(question1));
    }

    @Test
    void addQuestion_WithDuplicate_ShouldThrowException() {
        javaQuestionService.addQuestion("Вопрос", "Ответ");

        QuestionAlreadyAddedException exception = assertThrows(QuestionAlreadyAddedException.class,
                () -> javaQuestionService.addQuestion("Вопрос", "Ответ"));

        assertEquals("Вопрос уже существует", exception.getMessage());
    }

    // 2. Основные тесты на удаление
    @Test
    void removeQuestion_ShouldRemoveQuestion() {
        javaQuestionService.addQuestion(question1.getQuestion(), question1.getAnswer());
        javaQuestionService.addQuestion(question2.getQuestion(), question2.getAnswer());

        javaQuestionService.removeQuestion(question1.getQuestion(), question1.getAnswer());

        assertEquals(1, javaQuestionService.getQuestions().size());
        assertFalse(javaQuestionService.getQuestions().contains(question1));
    }

    @Test
    void removeQuestion_WithNonExistent_ShouldThrowException() {
        QuestionNotFoundException exception = assertThrows(QuestionNotFoundException.class,
                () -> javaQuestionService.removeQuestion("Несуществующий", "Вопрос"));

        assertEquals("Вопрос не найден", exception.getMessage());
    }

    // 3. Тесты на получение вопросов
    @Test
    void getQuestion_WithValidIndex_ShouldReturnQuestion() {
        javaQuestionService.addQuestion(question1.getQuestion(), question1.getAnswer());

        Question result = javaQuestionService.getQuestion(0);

        assertEquals(question1, result);
    }

    @Test
    void getQuestion_WithInvalidIndex_ShouldThrowException() {
        javaQuestionService.addQuestion(question1.getQuestion(), question1.getAnswer());

        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                () -> javaQuestionService.getQuestion(5));

        assertEquals("Неверный индекс: 5", exception.getMessage());
    }

    // 4. Главный тест на случайный вопрос
    @Test
    void getRandomQuestion_ShouldReturnRandomQuestion() {
        javaQuestionService.addQuestion(question1.getQuestion(), question1.getAnswer());
        javaQuestionService.addQuestion(question2.getQuestion(), question2.getAnswer());

        when(random.nextInt(2)).thenReturn(1);

        Question result = javaQuestionService.getRandomQuestion();

        assertEquals(question2, result);
        verify(random).nextInt(2);
    }

    @Test
    void getRandomQuestion_WithEmptyList_ShouldThrowException() {
        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> javaQuestionService.getRandomQuestion());

        assertEquals("Список вопросов пуст", exception.getMessage());
    }

    // 5.тест основного сценария
    @Test
    void integrationTest_FullWorkflow() {
        // Добавляем вопросы
        javaQuestionService.addQuestion(question1.getQuestion(), question1.getAnswer());
        javaQuestionService.addQuestion(question2.getQuestion(), question2.getAnswer());

        // Проверяем добавление
        assertEquals(2, javaQuestionService.getQuestions().size());

        // Получаем по индексу
        assertEquals(question1, javaQuestionService.getQuestion(0));

        // Тестируем случайный вопрос
        when(random.nextInt(2)).thenReturn(0);
        assertEquals(question1, javaQuestionService.getRandomQuestion());

        // Удаляем вопрос
        javaQuestionService.removeQuestion(question1.getQuestion(), question1.getAnswer());
        assertEquals(1, javaQuestionService.getQuestions().size());
    }
}


