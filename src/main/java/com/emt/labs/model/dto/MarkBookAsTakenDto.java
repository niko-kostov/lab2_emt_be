package com.emt.labs.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class MarkBookAsTakenDto implements Serializable {

    @NotNull
    private Long id;
}
