package com.fabrique.fabrique_app.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Survey implements Serializable {

    private static final long serialVersionUID = 1669890099509861844L;

    @Schema(description = "Survey Id",
            example = "1", required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long survey_id;

    @Schema(description = "Survey title",
            example = "title", required = true)
    @Column(name = "title")
    private String title;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss.SSS'Z'")
    @Schema(description = "Survey start date",
            example = "2021-12-28T20:22:26.389529", required = false)
    @Column(name = "start_date", nullable = true)
    private LocalDateTime startDate;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd'T'hh:mm:ss.SSS'Z'")
    @Schema(description = "Survey end date",
            example = "2021-12-28T20:22:26.389529", required = false)
    @Column(name = "end_date", nullable = true)
    private LocalDateTime endDate;

    @Schema(description = "Survey description",
            example = "description", required = false)
    @Column(name = "description", nullable = true)
    private String description;

    @Schema(description = "Survey active",
            example = "true", required = true)
    @Column(name = "active", nullable = true)
    private boolean active;

    /*@Schema(description = "List Question models", required = false)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    private List<Question> questions;*/

}
