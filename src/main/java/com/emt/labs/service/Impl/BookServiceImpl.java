package com.emt.labs.service.Impl;

import com.emt.labs.model.Author;
import com.emt.labs.model.Book;
import com.emt.labs.model.dto.AddBookDto;
import com.emt.labs.model.dto.EditBookDto;
import com.emt.labs.model.dto.GetAllBooksDto;
import com.emt.labs.model.dto.MarkBookAsTakenDto;
import com.emt.labs.model.enums.ECategory;
import com.emt.labs.model.exceptions.AuthorIdNotFoundException;
import com.emt.labs.model.exceptions.BookAmmountException;
import com.emt.labs.model.exceptions.BookIdNotFoundException;
import com.emt.labs.repository.AuthorRepository;
import com.emt.labs.repository.BookRepository;
import com.emt.labs.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public Book edit(EditBookDto editBookDto) {
        Book book = this.findById(editBookDto.getId());
        Author author = this.authorRepository.findById(editBookDto.getAuthorId()).
                orElseThrow(() -> new AuthorIdNotFoundException(editBookDto.getAuthorId()));

        book.setName(editBookDto.getName());
        book.setAvailableCopies(editBookDto.getAvailableCopies());
        book.setCategory(editBookDto.getCategory());
        book.setAuthor(author);

        return this.bookRepository.save(book);
    }

    @Override
    public Page<GetAllBooksDto> findAll(Pageable pageable) {
        ModelMapper mapper = new ModelMapper();

        return this.bookRepository.findAll(pageable).
                map(book -> mapper.map(book, GetAllBooksDto.class));
    }

    @Override
    public Book markBookAsTaken(MarkBookAsTakenDto markBookAsTakenDto) {
        Book book = this.findById(markBookAsTakenDto.getId());

        if (book.getAvailableCopies() > 0) {
            book.setAvailableCopies(book.getAvailableCopies() - 1);
        } else {
            throw new BookAmmountException(book.getId());
        }
        return this.bookRepository.save(book);
    }

    @Override
    public Book deleteById(Long id) {
        Book deletedBook = this.findById(id);

        this.bookRepository.delete(deletedBook);
        return deletedBook;
    }

    @Override
    public Book findById(Long id) {
        return this.bookRepository.findById(id).
                orElseThrow(() -> new BookIdNotFoundException(id));
    }

    @Override
    public Book add(AddBookDto addBookDto) {
        Author author = this.authorRepository.findById(addBookDto.getAuthorId()).
                orElseThrow(() -> new AuthorIdNotFoundException(addBookDto.getAuthorId()));

        Book book = new Book();
        book.setName(addBookDto.getName());
        book.setCategory(addBookDto.getCategory());
        book.setAvailableCopies(addBookDto.getAvailableCopies());
        book.setAuthor(author);

        return this.bookRepository.save(book);
    }

    @Override
    public List<ECategory> findAllCategories() {
        return List.of(ECategory.values());
    }
}
