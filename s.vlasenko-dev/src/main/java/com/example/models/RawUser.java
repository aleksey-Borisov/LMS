package com.example.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RawUser {
    @NotEmpty(message = "Login cannot be empty")
    private String login;
    @NotEmpty(message = "Password cannot be empty")
    private String password;
    @NotNull(message = "Role cannot be null")
    private Role role;
}
