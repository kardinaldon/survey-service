package com.fabrique.fabrique_app.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Question {

    @Schema(description = "question Id",
            example = "1", required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private long questionId;

    @Schema(description = "question text",
            example = "text", required = true)
    @Column(name = "text")
    private String text;

    @Schema(description = "question type - TEXT, SINGLE_CHOICE, MULTIPLE_CHOICE",
            example = "1", required = true)
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private TypeOfQuestion type;

    @Schema(description = "Survey",
            example = "1", required = true)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="survey_id")
    private Survey survey;


}
