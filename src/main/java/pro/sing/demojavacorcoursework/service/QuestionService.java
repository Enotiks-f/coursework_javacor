package pro.sing.demojavacorcoursework.service;

import pro.sing.demojavacorcoursework.model.Question;

import java.util.List;

public interface QuestionService {
    public Question getRandomQuestion();
    public List<Question> getQuestions();
    public void addQuestion(String question, String answer);
    public void removeQuestion(String question, String answer);
    public Question getQuestion(int index);
}
