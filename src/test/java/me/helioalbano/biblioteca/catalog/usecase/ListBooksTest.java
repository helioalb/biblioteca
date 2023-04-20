package me.helioalbano.biblioteca.catalog.usecase;

import me.helioalbano.biblioteca.catalog.domain.book.Book;
import me.helioalbano.biblioteca.catalog.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ListBooksTest {
  private BookRepository repository;
  private final static int INVALID_PAGE_NUMBER = -1;
  private final static int VALID_PAGE_NUMBER = 0;
  private final static int INVALID_NUMBER_OF_RESULTS_PER_PAGE = 0;
  private final static int VALID_NUMBER_OF_RESULTS_PER_PAGE = 2;

  @BeforeEach
  void setup() {
    repository = Mockito.mock(BookRepository.class);
  }

  @Test
  void givenInvalidPageNumber_whenExecute_thenShouldRaiseAnException() {
    var listBooks = new ListBooks(repository);

    Exception exception = assertThrows(IllegalArgumentException.class,
      () -> listBooks.execute(INVALID_PAGE_NUMBER, VALID_NUMBER_OF_RESULTS_PER_PAGE)
    );

    assertEquals("pageNumber invalid", exception.getMessage());
  }

  @Test
  void givenInvalidNumberOfResultsPerPage_whenExecute_thenShouldRaiseAnException() {
    var listBooks = new ListBooks(repository);

    Exception exception = assertThrows(IllegalArgumentException.class,
      () -> listBooks.execute(VALID_PAGE_NUMBER, INVALID_NUMBER_OF_RESULTS_PER_PAGE));

    assertEquals("numberOfResultsPerPage invalid", exception.getMessage());
  }

  @Test
  void givenThatNoExistsBooks_whenExecute_thenShouldReturnAEmptyList() {
    Mockito.when(repository.findAll(Mockito.any(), Mockito.any()))
      .thenReturn(Collections.emptyList());

    ListBooks listBooks = new ListBooks(repository);
    var books = listBooks.execute(VALID_PAGE_NUMBER, VALID_NUMBER_OF_RESULTS_PER_PAGE);

    assertTrue(books.isEmpty());
  }

  @Test
  void givenThatExistBooks_whenExecute_thenShouldReturnBooks() {
    Mockito.when(repository.findAll(Mockito.any(), Mockito.any()))
      .thenReturn(buildListOfBooks());

    var listBooks = new ListBooks(repository);
    var books = listBooks.execute(VALID_PAGE_NUMBER, VALID_NUMBER_OF_RESULTS_PER_PAGE);

    assertEquals(2, books.size());
  }

  private List<Book> buildListOfBooks() {
    var cleanCode = Book.load(1L, "Clean Code");
    var ddd = Book.load(2L, "Implementando Domain-Driven Design");

    return Arrays.asList(cleanCode, ddd);
  }
}
