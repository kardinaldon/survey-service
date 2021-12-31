package com.fabrique.fabrique_app.repository;

import com.fabrique.fabrique_app.model.entity.Question;
import com.fabrique.fabrique_app.model.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    Optional<Question> findById(long id);

    List<Question> findBySurvey(Survey survey);

    Question save(Question question);

}
