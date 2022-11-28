package me.helioalbano.biblioteca.catalog.adapter.database.postgres;

import me.helioalbano.biblioteca.catalog.domain.author.Author;
import me.helioalbano.biblioteca.catalog.repository.AuthorRepository;
import me.helioalbano.biblioteca.catalog.usecase.exceptions.AuthorNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class AuthorRepositoryPostgresTest {
  private final static Long INVALID_AUTHOR_ID = 999L;

  @Autowired()
  private AuthorRepository repository;

  @Test
  void givenAValidAuthor_whenCreate_thenReturnsTheSameAuthor() {
    var validAuthor = buildValidAuthor();

    var createdAuthor = repository.create(validAuthor);

    assertEquals(validAuthor.getName(), createdAuthor.getName());
  }

  private Author buildValidAuthor() {
    return Author.create("Martin Fowler");
  }

  @Test
  void givenAnInvalidAuthorId_whenFindById_thenRaiseAnException() {
    Exception exception =
      assertThrows(AuthorNotFoundException.class, () -> repository.findById(INVALID_AUTHOR_ID));

    assertNotNull(exception);
  }

  @Test
  void givenAnValidAuthorId_whenFindById_thenReturnAnAuthor() {
    var validAuthorId = getValidAuthorId();

    var author = repository.findById(validAuthorId);

    assertNotNull(author);
  }

  private Long getValidAuthorId() {
    var author = repository.create(buildValidAuthor());
    return author.getId();
  }

  @Test
  void givenADatabaseWithTwoAuthors_whenFindAll_thenReturnTwoAuthors() {
    insertTwoAuthorsInDatabase();

    var pageNumber = 0;
    var numberOfResultsPerPage = 10;
    var authors = repository.findAll(pageNumber, numberOfResultsPerPage);

    assertEquals(2, authors.size());
  }

  private void insertTwoAuthorsInDatabase() {
    repository.create(Author.create("Martin Fowler"));
    repository.create(Author.create("Robert C. Martin"));
  }
}
