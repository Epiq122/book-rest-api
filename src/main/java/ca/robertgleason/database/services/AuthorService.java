package ca.robertgleason.database.services;


import ca.robertgleason.database.domain.entities.Author;

import java.util.List;


public interface AuthorService {
    Author createAuthor(Author author);

    List<Author> findAll();
}
