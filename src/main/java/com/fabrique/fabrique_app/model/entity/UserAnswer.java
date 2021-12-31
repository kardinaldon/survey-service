package com.fabrique.fabrique_app.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class UserAnswer implements Serializable {

    @Schema(description = "Completed Survey Id",
            example = "1", required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_answer_id")
    private long UserAnswerId;

    /*@Schema(description = "Question model", required = true)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id")
    private Question question;*/

    @JsonIgnore
    @Schema(description = "Completed Survey", required = false, example = "1")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "completed_survey_id")
    private CompletedSurvey completedSurvey;

    @Schema(description = "Question text",
            example = "Question text", required = true)
    @Column(name="question")
    private String questionText;

    @Schema(description = "Answers",
            example = "Answer 1, Answer 2", required = true)
    @Column(name="answer")
    private String answer;


}
