package me.helioalbano.biblioteca.catalog.usecase.book;

import me.helioalbano.biblioteca.catalog.domain.book.Book;
import me.helioalbano.biblioteca.catalog.repository.BookRepository;
import me.helioalbano.biblioteca.catalog.usecase.exceptions.BookNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ShowBookTest {
  private BookRepository repository;
  private static final Long VALID_BOOK_ID = 1L;
  private static final Long INVALID_BOOK_ID = 1000L;

  @BeforeEach
  void setup() {
    repository = Mockito.mock(BookRepository.class);
  }

  @Test
  void givenAnInvalidBookId_whenExecute_thenShouldRaiseAnException() {
    var showBook = new ShowBook(repository);

    Mockito.when(repository.findById(INVALID_BOOK_ID))
      .thenThrow(new BookNotFoundException());

    Exception exception =
      assertThrows(BookNotFoundException.class, () -> showBook.execute(INVALID_BOOK_ID));

    assertEquals("book.not.found", exception.getMessage());
  }

  @Test
  void givenAValidBookId_whenExecute_thenShouldReturnABookOutput() {
    var showBook = new ShowBook(repository);

    Mockito.when(repository.findById(VALID_BOOK_ID))
      .thenReturn(buildBook());

    var bookOutput = showBook.execute(VALID_BOOK_ID);
    assertEquals(VALID_BOOK_ID, bookOutput.id());
    assertEquals("Clean code", bookOutput.title());
  }

  private Book buildBook() {
    return Book.load(VALID_BOOK_ID.longValue(), "Clean code");
  }
}
