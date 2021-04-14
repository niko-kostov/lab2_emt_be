package com.emt.labs.web;

import com.emt.labs.model.Author;
import com.emt.labs.model.dto.GetAuthorNameSurnameAndIdDto;
import com.emt.labs.service.AuthorService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "api/author", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<List<Author>> getAll() {
        return ResponseEntity.ok(this.authorService.findAll());
    }

    @DeleteMapping("/librarian/{id}")
    public ResponseEntity<Author> deleteAuthor(@PathVariable Long id) {
        return ResponseEntity.ok(this.authorService.deleteById(id));
    }

    @GetMapping("/librarian/getAuthorsForEdit")
    public ResponseEntity<List<GetAuthorNameSurnameAndIdDto>> getAuthorForEdit(){
        return ResponseEntity.ok(this.authorService.getAuthorsForEdit());
    }
}
