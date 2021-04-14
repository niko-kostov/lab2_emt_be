package com.emt.labs.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class GetAuthorNameSurnameAndIdDto implements Serializable {

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private Long id;
}
