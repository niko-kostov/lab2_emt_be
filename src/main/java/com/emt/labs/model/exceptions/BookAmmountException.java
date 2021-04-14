package com.emt.labs.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BookAmmountException extends RuntimeException {
    public BookAmmountException(Long id) {
        super(String.format("Book with id %d has ammount less or equal to 0", id));
    }
}