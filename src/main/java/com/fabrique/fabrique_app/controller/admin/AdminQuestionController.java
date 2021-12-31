package com.fabrique.fabrique_app.controller.admin;

import com.fabrique.fabrique_app.model.entity.Question;
import com.fabrique.fabrique_app.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
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

@Tag(name = "Admin Questions Rest Controller")
@RestController
@RequestMapping("admin/question")
public class AdminQuestionController {

    @Autowired
    private QuestionService questionService;

    @Operation(summary = "create a question")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "question created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "Not Authorized!"),
            @ApiResponse(responseCode = "400", description = "type and text are required fields"),
            @ApiResponse(responseCode = "403", description = "Forbidden!"),
            @ApiResponse(responseCode = "404", description = "Not Found!")})
    @PostMapping(path ="/create"
            , consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createQuestion(@RequestBody Question question) {
        if(question.getText() != null && question.getType() != null){
            questionService.addQuestion(question);
            return ResponseEntity.ok("question created");
        }
        else{
            return ResponseEntity.badRequest().body("type and text are required fields");
        }
    }

    @Operation(summary = "update question")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "question changed",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "Not Authorized!"),
            @ApiResponse(responseCode = "400", description = "question id not specified"),
            @ApiResponse(responseCode = "403", description = "Forbidden!"),
            @ApiResponse(responseCode = "404", description = "Not Found!")})
    @PutMapping(path ="/update"
            , consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> updateQuestion(@RequestBody Question question) {
        if(question.getQuestionId() != 0) {
            Optional<Question> byId = questionService.findById(question.getQuestionId());
            if(byId.isPresent()){
                questionService.updateQuestion(question);
                return ResponseEntity.ok("question changed");
            }
            else{
                return ResponseEntity.notFound().build();
            }
        }
        else{
            return ResponseEntity.badRequest().body("question id not specified");
        }
    }

    @Operation(summary = "delete question")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "question changed",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "Not Authorized!"),
            @ApiResponse(responseCode = "400", description = "question id not specified"),
            @ApiResponse(responseCode = "403", description = "Forbidden!"),
            @ApiResponse(responseCode = "404", description = "Not Found!")})
    @DeleteMapping(path ="/delete"
            , consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteQuestion(@RequestParam long id) {
        if(id != 0) {
            Optional<Question> byId = questionService.findById(id);
            if(byId.isPresent()){
                questionService.deleteQuestion(byId.get());
                return ResponseEntity.ok("question deleted");
            }
            else{
                return ResponseEntity.notFound().build();
            }
        }
        else{
            return ResponseEntity.badRequest().body("question id not specified");
        }
    }
}
