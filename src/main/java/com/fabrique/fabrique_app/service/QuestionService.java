package com.fabrique.fabrique_app.service;

import com.fabrique.fabrique_app.model.entity.Question;
import com.fabrique.fabrique_app.model.entity.Survey;
import com.fabrique.fabrique_app.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public void addQuestion(Question question){
        questionRepository.save(question);
    }

    public void updateQuestion(Question question){
        questionRepository.save(question);
    }

    public void deleteQuestion(Question question){
        questionRepository.delete(question);
    }

    public Optional<Question> findById(long id){
        return questionRepository.findById(id);
    }

    public List<Question> findBySurvey(Survey survey){
        return questionRepository.findBySurvey(survey);
    }

}
