package com.emt.labs.model.dto;

import com.emt.labs.model.enums.ECategory;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class EditBookDto implements Serializable {

    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private ECategory category;

    @NotNull
    private int availableCopies;

    @NotNull
    private Long authorId;
}
