package me.helioalbano.biblioteca.catalog.adapter.database.postgres;

import me.helioalbano.biblioteca.catalog.domain.book.Book;
import me.helioalbano.biblioteca.catalog.domain.book.Title;
import me.helioalbano.biblioteca.catalog.repository.BookRepository;
import me.helioalbano.biblioteca.catalog.usecase.exceptions.BookNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class BookRepositoryPostgresTest {
  private static final Long INVALID_BOOK_ID = 999L;

  @Autowired()
  private BookRepository repository;

  @Test
  void givenADatabaseWithTwoBooks_whenFindAll_thenReturnTwoBooks() {
    insertTwoBooksInDatabase();

    var pageNumber = 0;
    var numberOfResultsPerPage = 10;
    var books = repository.findAll(pageNumber, numberOfResultsPerPage);

    assertEquals(2, books.size());
  }

  private void insertTwoBooksInDatabase() {
    repository.save(Book.create("Clean code"));
    repository.save(Book.create("Implementando Domain-Driven Design"));
  }

  @Test
  void givenAnInvalidBookId_whenFindById_thenShouldRaiseAnException() {
    Exception exception =
      assertThrows(BookNotFoundException.class, () -> repository.findById(INVALID_BOOK_ID));

    assertNotNull(exception);
  }

  @Test
  void givenANewTitleToABook_whenSave_thenUpdateTitle() {
    var newTitle = "Implementando Domain-Driven Design";
    var book = repository.save(Book.create("Domain-Driven Design"));
    book.setTitle(new Title(newTitle));
    assertEquals(newTitle, repository.save(book).getTitle().toString());
  }
}
