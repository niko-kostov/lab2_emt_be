package com.emt.labs.service.Impl;

import com.emt.labs.model.Author;
import com.emt.labs.model.dto.GetAuthorNameSurnameAndIdDto;
import com.emt.labs.model.exceptions.AuthorIdNotFoundException;
import com.emt.labs.repository.AuthorRepository;
import com.emt.labs.service.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public Author deleteById(Long id) {
        Author deletedAuthor = this.findById(id);
        this.authorRepository.delete(deletedAuthor);
        return deletedAuthor;
    }

    @Override
    public Author findById(Long id) {
        return this.authorRepository.findById(id).
                orElseThrow(() -> new AuthorIdNotFoundException(id));
    }

    @Override
    public List<GetAuthorNameSurnameAndIdDto> getAuthorsForEdit() {
        ModelMapper modelMapper = new ModelMapper();

        return this.findAll().stream().map(author -> modelMapper.map(author, GetAuthorNameSurnameAndIdDto.class)).
                collect(Collectors.toList());
    }
}
