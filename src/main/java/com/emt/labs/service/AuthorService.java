package com.emt.labs.service;

import com.emt.labs.model.Author;
import com.emt.labs.model.dto.GetAuthorNameSurnameAndIdDto;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();

    Author deleteById(Long id);

    Author findById(Long id);

    List<GetAuthorNameSurnameAndIdDto> getAuthorsForEdit();
}