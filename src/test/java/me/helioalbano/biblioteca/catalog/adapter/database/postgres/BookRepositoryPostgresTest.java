package me.helioalbano.biblioteca.catalog.adapter.database.postgres;

import me.helioalbano.biblioteca.catalog.domain.book.Book;
import me.helioalbano.biblioteca.catalog.repository.BookRepository;
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
    repository.create(Book.create("Clean code"));
    repository.create(Book.create("Implementando Domain-Driven Design"));
  }
}
