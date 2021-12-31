package com.fabrique.fabrique_app.service;

import com.fabrique.fabrique_app.model.entity.Survey;
import com.fabrique.fabrique_app.repository.SurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;


    public Survey addSurvey(Survey survey){
        return surveyRepository.save(survey);
    }

    public Optional<Survey> getByIdSurvey(long id){
        return surveyRepository.findById(id);
    }

    public List<Survey> findAllActiveSurvey(){
        return surveyRepository.findByActive(true);
    }

    public Survey updateSurvey(Survey survey){
        return surveyRepository.save(survey);
    }

    public void deleteSurvey(Survey survey){
        surveyRepository.delete(survey);
    }

}
