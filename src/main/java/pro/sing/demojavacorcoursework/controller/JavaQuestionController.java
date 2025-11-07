package pro.sing.demojavacorcoursework.controller;


import org.springframework.web.bind.annotation.*;
import pro.sing.demojavacorcoursework.model.Question;
import pro.sing.demojavacorcoursework.service.serviceimple.JavaQuestionService;

import java.util.List;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {

    private final JavaQuestionService javaQuestionService;

    public JavaQuestionController(JavaQuestionService javaQuestionService) {
        this.javaQuestionService = javaQuestionService;
    }

    @GetMapping
    public List<Question> getQuestions() {
        return javaQuestionService.getQuestions();
    }

    @GetMapping("/add")
    public String addQuestion(@RequestParam String question, @RequestParam String answer) {
        javaQuestionService.addQuestion(question, answer);
        return "Вопрос: " + question +  " успешно добавлены";
    }

    @GetMapping("/remove")
    public String removeQuestion(@RequestParam String question, @RequestParam String answer) {
        javaQuestionService.removeQuestion(question,  answer);
        return "Вопрос: " + question + " удален";
    }

}
