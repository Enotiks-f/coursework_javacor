package pro.sing.demojavacorcoursework.service;

import pro.sing.demojavacorcoursework.model.Question;

import java.util.List;

public interface QuestionService {
    public Question getRandomQuestion();
    public List<Question> getQuestions();
    public void addQuestion(Question question);
    public void removeQuestion(Question question);
    public Question getQuestion(int index);
}
