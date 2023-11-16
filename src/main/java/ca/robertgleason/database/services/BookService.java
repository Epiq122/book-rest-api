package ca.robertgleason.database.services;

import ca.robertgleason.database.domain.entities.Book;

public interface BookService {

    Book createBook(String isbn, Book book);
}
