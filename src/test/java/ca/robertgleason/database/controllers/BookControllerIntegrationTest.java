package ca.robertgleason.database.controllers;


import ca.robertgleason.database.TestDataUtil;
import ca.robertgleason.database.domain.entities.Book;
import ca.robertgleason.database.domain.entities.dto.BookDto;
import ca.robertgleason.database.services.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class BookControllerIntegrationTest {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    private final BookService bookService;

    @Autowired
    public BookControllerIntegrationTest(MockMvc mockMvc, ObjectMapper objectMapper, BookService bookService) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.bookService = bookService;
    }

    @Test
    public void testThatCreateBookSuccessfullyReturnsHttp201Created() throws Exception {
        BookDto bookDto = TestDataUtil.createTestBookDtoA(null);
        String bookJson = objectMapper.writeValueAsString(bookDto);
        mockMvc.perform(put("/books/" + bookDto.getIsbn())
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );

    }

    @Test
    public void testThatCreateBookSuccessfullyReturnsCreatedBook() throws Exception {
        BookDto bookDto = TestDataUtil.createTestBookDtoA(null);
        String bookJson = objectMapper.writeValueAsString(bookDto);
        mockMvc.perform(put("/books/" + bookDto.getIsbn())
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value(bookDto.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value(bookDto.getTitle())
        );


    }


    @Test
    public void testThatListBooksReturnsHttpStatus200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatListBooksReturnsBook() throws Exception {
        Book testBook = TestDataUtil.createTestBookA(null);
        bookService.createBook(testBook.getIsbn(), testBook);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].isbn").value(testBook.getIsbn())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].title").value(testBook.getTitle()
                ));

    }

    @Test
    public void testThatGetBooksReturnsHttpStatus404NotExists() throws Exception {
        Book testBook = TestDataUtil.createTestBookA(null);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/" + testBook.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatGetBooksReturnsHttpStatus200WhenBookExists() throws Exception {
        Book testBook = TestDataUtil.createTestBookA(null);
        bookService.createBook(testBook.getIsbn(), testBook);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books/" + testBook.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value("666-666-6-66-11-2")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value("HarshGallows")
        );
    }

}
