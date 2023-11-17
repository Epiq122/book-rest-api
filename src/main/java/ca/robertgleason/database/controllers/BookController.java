package ca.robertgleason.database.controllers;


import ca.robertgleason.database.domain.entities.Book;
import ca.robertgleason.database.domain.entities.dto.BookDto;
import ca.robertgleason.database.mappers.Mapper;
import ca.robertgleason.database.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class BookController {

    private Mapper<Book, BookDto> bookMapper;

    private BookService bookService;

    public BookController(Mapper<Book, BookDto> bookMapper, BookService bookService) {
        this.bookMapper = bookMapper;
        this.bookService = bookService;
    }

    @PutMapping("/books/{isbn}")
    public ResponseEntity<BookDto> createBook(@PathVariable("isbn") String isbn, @RequestBody BookDto bookDto) {
        Book book = bookMapper.mapFrom(bookDto);
        Book savedBook = bookService.createBook(isbn, book);
        BookDto savedBookDto = bookMapper.mapTo(savedBook);
        return new ResponseEntity<>(savedBookDto, HttpStatus.CREATED);


    }

    @GetMapping("/books")
    public List<BookDto> listBooks() {
        List<Book> books = bookService.findAll();
        return books.stream().map(bookMapper::mapTo).collect(Collectors.toList());
    }


    @GetMapping("/books/{isbn}")
    public ResponseEntity<BookDto> getBook(@PathVariable("isbn") String isbn) {
        Optional<Book> foundBook = bookService.findOne(isbn);
        return foundBook.map(book -> new ResponseEntity<>(bookMapper.mapTo(book), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
