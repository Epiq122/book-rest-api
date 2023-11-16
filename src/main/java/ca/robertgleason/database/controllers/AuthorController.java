package ca.robertgleason.database.controllers;


import ca.robertgleason.database.domain.entities.Author;
import ca.robertgleason.database.domain.entities.dto.AuthorDto;
import ca.robertgleason.database.mappers.Mapper;
import ca.robertgleason.database.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AuthorController {

    private AuthorService authorService;
    private Mapper<Author, AuthorDto> authorMapper;

    public AuthorController(AuthorService authorService, Mapper<Author, AuthorDto> authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }


    @PostMapping("/authors")
    public ResponseEntity<AuthorDto> creatAuthor(@RequestBody AuthorDto authorDto) {
        Author author = authorMapper.mapFrom(authorDto);
        Author savedAuthor = authorService.createAuthor(author);
        return new ResponseEntity<>(authorMapper.mapTo(savedAuthor), HttpStatus.CREATED);
    }

    @GetMapping("/authors")
    public List<AuthorDto> listAuthors() {
        List<Author> authors = authorService.findAll();
        return authors.stream().map(authorMapper::mapTo).collect(Collectors.toList());


    }

}
