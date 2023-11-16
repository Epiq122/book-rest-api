package ca.robertgleason.database.services.impl;

import ca.robertgleason.database.domain.entities.Book;
import ca.robertgleason.database.repositories.BookRepository;
import ca.robertgleason.database.services.BookService;
import org.springframework.stereotype.Service;


@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book createBook(String isbn, Book book) {
        book.setIsbn(isbn);
        return bookRepository.save(book);
    }
}
