package ca.robertgleason.database.controllers;


import ca.robertgleason.database.domain.entities.Author;
import ca.robertgleason.database.domain.entities.dto.AuthorDto;
import ca.robertgleason.database.mappers.Mapper;
import ca.robertgleason.database.services.AuthorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

    private AuthorService authorService;
    private Mapper<Author, AuthorDto> authorMapper;

    public AuthorController(AuthorService authorService, Mapper<Author, AuthorDto> authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }


    @PostMapping("/authors")
    public AuthorDto creatAuthor(@RequestBody AuthorDto authorDto) {
        Author author = authorMapper.mapFrom(authorDto);
        Author savedAuthor = authorService.createAuthor(author);
        return authorMapper.mapTo(savedAuthor);
    }

}
