package pro.sing.demojavacorcoursework.service;

import pro.sing.demojavacorcoursework.model.Question;

import java.util.List;

public interface ExaminerService {
    public List<String> getQuestions(int question);
}
