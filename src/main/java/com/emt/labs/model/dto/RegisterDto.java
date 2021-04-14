package com.emt.labs.model.dto;

import com.emt.labs.model.enums.ERole;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RegisterDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String role;
}