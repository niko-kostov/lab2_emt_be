package com.emt.labs.model.dto;

import com.emt.labs.model.enums.ECategory;
import lombok.Data;

import java.io.Serializable;

@Data
public class AddBookDto implements Serializable {

    private String name;

    private ECategory category;

    private int availableCopies;

    private Long authorId;
}
