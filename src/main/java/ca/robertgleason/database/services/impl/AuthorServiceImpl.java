package ca.robertgleason.database.services.impl;


import ca.robertgleason.database.domain.entities.Author;
import ca.robertgleason.database.repositories.AuthorRepository;
import ca.robertgleason.database.services.AuthorService;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }
}
