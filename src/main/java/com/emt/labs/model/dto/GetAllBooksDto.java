package com.emt.labs.model.dto;

import com.emt.labs.model.enums.ECategory;
import lombok.Data;

import java.io.Serializable;

@Data
public class GetAllBooksDto implements Serializable {

    private Long id;

    private String name;

    private ECategory category;

    private int availableCopies;

    private String authorName;

    private String authorSurname;

    private Long authorId;
}
