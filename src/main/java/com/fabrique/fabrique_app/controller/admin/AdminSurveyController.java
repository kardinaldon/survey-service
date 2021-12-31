package com.fabrique.fabrique_app.controller.admin;

import com.fabrique.fabrique_app.model.entity.Survey;
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
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Tag(name = "Admin survey Rest Controller")
@RestController
@RequestMapping("admin/survey")
public class AdminSurveyController {

    @Autowired
    private SurveyService surveyService;

    @Operation(summary = "add new Survey")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Survey.class))),
            @ApiResponse(responseCode = "401", description = "Not Authorized!"),
            @ApiResponse(responseCode = "403", description = "Forbidden!"),
            @ApiResponse(responseCode = "404", description = "Not Found!")})
    @PostMapping(path = "/add"
            , consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Survey> addSurvey(@RequestBody Survey survey) {
        return ResponseEntity.ok().body(surveyService.addSurvey(survey));
    }

    @Operation(summary = "update Survey")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "Not Authorized!"),
            @ApiResponse(responseCode = "400", description = "Survey with this id not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden!"),
            @ApiResponse(responseCode = "404", description = "Not Found!")})
    @PutMapping(path = "/update"
            , consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateSurvey(@RequestBody Survey survey) {
        Optional<Survey> byIdSurvey = surveyService.getByIdSurvey(survey.getSurvey_id());
        if(byIdSurvey.isPresent()){
            survey.setStartDate(byIdSurvey.get().getStartDate());
            surveyService.updateSurvey(survey);
            return ResponseEntity.ok("survey changed");
        }
        else{
            return ResponseEntity.badRequest().body("survey with this id not found");
        }
    }

    @Operation(summary = "delete Survey by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "survey successfully deleted",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "Not Authorized!"),
            @ApiResponse(responseCode = "400", description = "Survey with this id not found/id is not specified"),
            @ApiResponse(responseCode = "403", description = "Forbidden!"),
            @ApiResponse(responseCode = "404", description = "Not Found!")})
    @DeleteMapping(path = "/delete")
    public ResponseEntity<String> deleteSurveyById(@Parameter(description="survey ID")
                                                        @RequestParam long id) {
        if(id != 0) {
            Optional<Survey> surveyOptional = surveyService.getByIdSurvey(id);
            if(surveyOptional.isPresent()){
                surveyService.deleteSurvey(surveyOptional.get());
                return ResponseEntity.ok("survey successfully deleted");
            }
            else{
                return ResponseEntity.badRequest().body("survey with this id not found");
            }
        }
        else {
            return ResponseEntity.badRequest().body("id is not specified");
        }
    }
}
