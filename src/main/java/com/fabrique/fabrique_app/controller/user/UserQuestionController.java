package com.fabrique.fabrique_app.controller.user;

import com.fabrique.fabrique_app.model.entity.Question;
import com.fabrique.fabrique_app.model.entity.Survey;
import com.fabrique.fabrique_app.service.QuestionService;
import com.fabrique.fabrique_app.service.SurveyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Tag(name = "User Questions Rest Controller")
@RestController
@RequestMapping("user/question")
public class UserQuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private SurveyService surveyService;

    @Operation(summary = "get a question by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "question created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Question.class))),
            @ApiResponse(responseCode = "401", description = "Not Authorized!"),
            @ApiResponse(responseCode = "400", description = "question id not specified"),
            @ApiResponse(responseCode = "403", description = "Forbidden!"),
            @ApiResponse(responseCode = "404", description = "Not Found!")})
    @GetMapping(path ="/by_id"
            , consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Question> getQuestionById(@Parameter(description="question ID")
                                                    @RequestParam long id) {
        if(id != 0){
            Optional<Question> byId = questionService.findById(id);
            if(byId.isPresent()){
                return ResponseEntity.ok(byId.get());
            }
            else {
                return ResponseEntity.notFound().build();
            }
        }
        else{
            return ResponseEntity.badRequest().body(new Question());
        }
    }

    @Operation(summary = "get a questions by survey id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "su",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Question.class))),
            @ApiResponse(responseCode = "401", description = "Not Authorized!"),
            @ApiResponse(responseCode = "400", description = "survey id not specified"),
            @ApiResponse(responseCode = "403", description = "Forbidden!"),
            @ApiResponse(responseCode = "404", description = "Not Found!")})
    @GetMapping(path ="/by_survey_id"
            , consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Question>> getQuestionBySurveyId(@Parameter(description="survey ID")
                                                          @RequestParam long id) {
        if(id != 0){
            Optional<Survey> byId = surveyService.getByIdSurvey(id);
            return byId.map(survey -> ResponseEntity.ok(questionService.findBySurvey(survey)))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        }
        else{
            return ResponseEntity.badRequest().body(Collections.emptyList());
        }
    }


}
