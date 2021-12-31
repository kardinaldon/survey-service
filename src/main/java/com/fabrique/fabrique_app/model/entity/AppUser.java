package com.fabrique.fabrique_app.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Table(name="app_user")
public class AppUser implements Serializable {

    private static final long serialVersionUID = -3280611442267903998L;

    @Schema(description = "User Id",
            example = "1", required = false)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "app_user_id")
    private long appUserId;

    @Schema(description = "User login",
            example = "user", required = true)
    @Column(name = "login")
    private String login;

    @JsonIgnore
    @Schema(description = "User password",
            example = "password", required = true)
    @Column(name = "password")
    private String password;

    @JsonIgnore
    @JsonFormat
            (shape = JsonFormat.Shape.STRING)
    @Schema(description = "User role",
            example = "USER", required = true)
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;


}
