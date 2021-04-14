package com.emt.labs.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookIdNotFoundException extends RuntimeException {
    public BookIdNotFoundException(Long id) {
        super(String.format("Book with id %d not found", id));
    }
}