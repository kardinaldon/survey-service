package com.fabrique.fabrique_app.controller.anonymous;

import com.fabrique.fabrique_app.model.entity.CompletedSurvey;
import com.fabrique.fabrique_app.model.entity.UserAnswer;
import com.fabrique.fabrique_app.service.AnswerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Controller for anonymous users")
@RestController
@RequestMapping("anonymous/survey")
public class AnonymousSurveyController {

    @Autowired
    private AnswerService answerService;

    @Operation(summary = "take Surveys")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The survey was successfully completed",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "400", description = "Survey with this id not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden!"),
            @ApiResponse(responseCode = "404", description = "Not Found!")})
    @PostMapping(path = "/take"
            , consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> takeSurvey(@RequestBody CompletedSurvey completedSurvey){
        List<UserAnswer> userAnswerList = completedSurvey.getUserAnswerList();
        if(completedSurvey.getUser() != null
                && completedSurvey.getSurvey().getSurvey_id() != 0
                && userAnswerList.size() != 0){
            userAnswerList.forEach(userAnswer -> {
                userAnswer.setCompletedSurvey(completedSurvey);
            });
            answerService.addCompletedSurvey(completedSurvey);
            return ResponseEntity.ok().body("The survey was successfully completed");
        }
        else{
            return ResponseEntity.badRequest().body("Survey with this id not found");
        }
    }

}
