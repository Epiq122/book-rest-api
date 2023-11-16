package ca.robertgleason.database;

import ca.robertgleason.database.domain.entities.Author;
import ca.robertgleason.database.domain.entities.Book;
import ca.robertgleason.database.domain.entities.dto.AuthorDto;
import ca.robertgleason.database.domain.entities.dto.BookDto;

public final class TestDataUtil {
    private TestDataUtil() {
    }

    public static Author createTestAuthorA() {
        return Author.builder()
                .id(1L)
                .name("Hershal Grove")
                .age(66)
                .build();
    }

    public static Author createTestAuthorB() {
        return Author.builder()
                .id(2L)
                .name("Jerry Leary")
                .age(16)
                .build();
    }

    public static Author createTestAuthorC() {
        return Author.builder()
                .id(3L)
                .name("Merganzer")
                .age(34)
                .build();
    }


    public static Book createTestBookA(final Author author) {
        return Book.builder()
                .isbn("666-666-6-66-11-2")
                .title("HarshGallows")
                .author(author)
                .build();
    }

    public static Book createTestBookB(final Author author) {
        return Book.builder()
                .isbn("9-116-6-66-11-2")
                .title("Jumbo Joe")
                .author(author)
                .build();
    }

    public static Book createTestBookC(final Author author) {
        return Book.builder()
                .isbn("622-1566-1-36-13-2")
                .title("Jelously Orchestrate")
                .author(author)
                .build();
    }


    public static BookDto createTestBookDtoA(final AuthorDto author) {
        return BookDto.builder()
                .isbn("9-116-6-66-11-2")
                .title("Jumbo Joe")
                .author(author)
                .build();
    }
}
