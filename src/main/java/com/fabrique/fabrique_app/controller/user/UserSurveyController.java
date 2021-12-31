package com.fabrique.fabrique_app.controller.user;

import com.fabrique.fabrique_app.model.entity.AppUser;
import com.fabrique.fabrique_app.model.entity.Survey;
import com.fabrique.fabrique_app.model.entity.CompletedSurvey;
import com.fabrique.fabrique_app.service.AnswerService;
import com.fabrique.fabrique_app.service.SurveyService;
import com.fabrique.fabrique_app.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "User survey Rest Controller")
@RestController
@RequestMapping("user/survey")
public class UserSurveyController {

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private UserService userService;

    @Operation(summary = "Get All Active Surveys")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Survey.class)))),
            @ApiResponse(responseCode = "401", description = "Not Authorized!"),
            @ApiResponse(responseCode = "403", description = "Forbidden!"),
            @ApiResponse(responseCode = "404", description = "Not Found!")})
    @GetMapping(value = "/active", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Survey>> getActiveSurveys() {
        return ResponseEntity.ok().body(surveyService.findAllActiveSurvey());
    }

    @GetMapping(value = "/answer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CompletedSurvey>> getUserCompletedSurveys() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            Optional<AppUser> byLogin = userService.findByLogin(authentication.getName());
            if(byLogin.isPresent()){
                return ResponseEntity.ok().body(answerService.getAllUserCompletedSurveys(byLogin.get()));
            }
            else{
                return ResponseEntity.notFound().build();
            }
        }
        else {
            return ResponseEntity.notFound().build();
        }

    }
}
