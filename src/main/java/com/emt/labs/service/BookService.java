package com.emt.labs.service;

import com.emt.labs.model.Book;
import com.emt.labs.model.dto.AddBookDto;
import com.emt.labs.model.dto.EditBookDto;
import com.emt.labs.model.dto.GetAllBooksDto;
import com.emt.labs.model.dto.MarkBookAsTakenDto;
import com.emt.labs.model.enums.ECategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    Book edit(EditBookDto editBookDto);

    Page<GetAllBooksDto> findAll(Pageable pageable);

    Book markBookAsTaken(MarkBookAsTakenDto markBookAsTakenDto);

    Book deleteById(Long id);

    Book findById(Long id);

    Book add(AddBookDto addBookDto);

    List<ECategory> findAllCategories();
}
