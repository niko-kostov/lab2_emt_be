package com.emt.labs.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Data
public class JwtResponseDto implements Serializable {

    private String accessToken;

    private String tokenType = "Bearer";

    @NotBlank
    private String username;

    private List<String> roles;
}
