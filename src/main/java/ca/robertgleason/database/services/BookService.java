package ca.robertgleason.database.services;

import ca.robertgleason.database.domain.entities.Book;

import java.util.List;

public interface BookService {

    Book createBook(String isbn, Book book);

    List<Book> findAll();
}
