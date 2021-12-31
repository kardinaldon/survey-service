package com.fabrique.fabrique_app.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class CompletedSurvey implements Serializable {

    private static final long serialVersionUID = 3454710487667903338L;

    @Schema(description = "Completed Survey Id",
            example = "1", required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="completed_survey_id")
    private long completedSurveyId;

    @JsonIgnore
    @Schema(description = "User, who completed the survey",
            example = "1", required = true)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "app_user_id")
    private AppUser user;

    @Schema(description = "Survey",
            example = "1", required = true)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="survey_id")
    private Survey survey;

    @Schema(description = "List User Answer models", required = false)
    @OneToMany(mappedBy = "completedSurvey", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@JoinColumn(name = "user_answer_id")
    private List<UserAnswer> userAnswerList = new ArrayList<UserAnswer>();


}
