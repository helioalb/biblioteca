package me.helioalbano.biblioteca.catalog.adapter.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.helioalbano.biblioteca.catalog.adapter.rest.dto.CreateBookRequest;
import me.helioalbano.biblioteca.catalog.usecase.book.CreateBook;
import me.helioalbano.biblioteca.catalog.usecase.book.ListBooks;
import me.helioalbano.biblioteca.catalog.usecase.book.ShowBook;
import me.helioalbano.biblioteca.catalog.usecase.book.dto.BookOutput;
import me.helioalbano.biblioteca.catalog.usecase.exceptions.BookNotFoundException;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerTest {
  private static final String RESOURCE = "/catalog/books/";
  private static final Integer VALID_BOOK_ID = 1;
  private static final Integer INVALID_BOOK_ID = 999;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper mapper;

  @MockBean
  private CreateBook createBook;

  @MockBean
  private ListBooks listBooks;

  @MockBean
  private ShowBook showBook;

  @Test
  void createBook_success() throws Exception {
    var request = new CreateBookRequest();
    request.setTitle("O Programador Pragmático: de aprendiz a mestre");

    when(createBook.execute(any())).thenReturn(10L);

    var mockRequest = MockMvcRequestBuilders.post("/catalog/books/")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsString(request));

    mockMvc.perform(mockRequest)
        .andExpect(status().isCreated())
        .andExpect(header().string("Location", "/catalog/books/10"));
  }

  @Test
  void createBook_emptyTitle_error() throws Exception {
    var request = new CreateBookRequest();
    request.setTitle(null);
    var mockRequest = MockMvcRequestBuilders.post("/catalog/books/")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsString(request));

    mockMvc.perform(mockRequest)
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.title", Is.is("The book title must be informed")));
  }

  @Test
  void createBook_titleLengthLessThan3_error() throws Exception {
    var request = new CreateBookRequest();
    request.setTitle("Oi");
    var mockRequest = MockMvcRequestBuilders.post("/catalog/books/")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsString(request));

    mockMvc.perform(mockRequest)
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.title", Is.is("size must be between 3 and 50")));
  }

  @Test
  void givenRequestToGetAllBooks_whenRequesting_thenReturnAllBooks() throws Exception {
    var request = buildRequestToAllBooks();

    mockMvc.perform(request)
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$[0].id").value(VALID_BOOK_ID));
  }

  private MockHttpServletRequestBuilder buildRequestToAllBooks() {
    when(listBooks.execute(any(), any())).thenReturn(Arrays.asList(buildOneBook()));

    return MockMvcRequestBuilders.get(RESOURCE)
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON);
  }

  private BookOutput buildOneBook() {
    return new BookOutput(VALID_BOOK_ID.longValue(), "Clean Code");
  }

  @Test
  void givenAnInvalidRequestToGetOneBook_whenQueryOnBook_thenRaiseAnException() throws Exception {
    var request = buildInvalidRequestToGetOneBook();

    mockMvc.perform(request)
      .andExpect(status().isNotFound())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

  private MockHttpServletRequestBuilder buildInvalidRequestToGetOneBook() {
    when(showBook.execute(any(Long.class))).thenThrow(new BookNotFoundException());

    return MockMvcRequestBuilders.get(RESOURCE + INVALID_BOOK_ID)
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON);
  }

  @Test
  void givenAValidRequestToGetOneBook_whenQueryOnBook_thenReturnOnBook() throws Exception {
    var request = buildValidRequestToGetOneBook();

    mockMvc.perform(request)
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

  private MockHttpServletRequestBuilder buildValidRequestToGetOneBook() {
    when(showBook.execute(any(Long.class))).thenReturn(buildOneBook());

    return MockMvcRequestBuilders.get(RESOURCE + INVALID_BOOK_ID)
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON);
  }
}
