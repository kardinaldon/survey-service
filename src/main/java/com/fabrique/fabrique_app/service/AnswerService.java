package com.fabrique.fabrique_app.service;


import com.fabrique.fabrique_app.model.entity.AppUser;
import com.fabrique.fabrique_app.model.entity.CompletedSurvey;
import com.fabrique.fabrique_app.model.entity.UserAnswer;
import com.fabrique.fabrique_app.repository.CompletedSurveyRepository;
import com.fabrique.fabrique_app.repository.UserAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AnswerService {

    @Autowired
    private CompletedSurveyRepository answerRepository;

    @Autowired
    private UserAnswerRepository userAnswerRepository;

    public CompletedSurvey addCompletedSurvey(CompletedSurvey completedSurvey){
        return answerRepository.save(completedSurvey);
    }

    public List<CompletedSurvey> getAllUserCompletedSurveys(AppUser user){
        return answerRepository.getByUser(user);
    }

    public UserAnswer addUserAnswer(UserAnswer userAnswer){
        return userAnswerRepository.save(userAnswer);
    }

}
