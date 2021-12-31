package com.fabrique.fabrique_app.repository;

import com.fabrique.fabrique_app.model.entity.AppUser;
import com.fabrique.fabrique_app.model.entity.CompletedSurvey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompletedSurveyRepository extends JpaRepository<CompletedSurvey, Long> {

    List<CompletedSurvey> getByUser(AppUser user);

    CompletedSurvey save(CompletedSurvey userAnswer);
}
