package me.helioalbano.biblioteca.catalog.usecase;

import me.helioalbano.biblioteca.catalog.domain.entity.author.Author;
import me.helioalbano.biblioteca.catalog.domain.repository.AuthorRepository;
import me.helioalbano.biblioteca.catalog.usecase.dto.AuthorOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ListAuthorsTest {
  private AuthorRepository authorRepository;
  private final static int INVALID_PAGE_NUMBER = -1;
  private final static int VALID_PAGE_NUMBER = 0;
  private final static int INVALID_NUMBER_OF_RESULTS_PER_PAGE = 0;
  private final static int VALID_NUMBER_OF_RESULTS_PER_PAGE = 2;

  @BeforeEach
  void setup() {
    authorRepository = mock(AuthorRepository.class);
  }

  @Test
  void givenInvalidPageNumber_whenExecute_thenShouldRaiseAnException() {
    var listAuthors = new ListAuthors(authorRepository);

    Exception exception = assertThrows(
      IllegalArgumentException.class,
      () -> listAuthors.execute(INVALID_PAGE_NUMBER, VALID_NUMBER_OF_RESULTS_PER_PAGE)
    );

    assertEquals("pageNumber invalid", exception.getMessage());
  }

  @Test
  void givenInvalidNumberOfResultsPerPage_whenExecute_thenShouldRaiseAnException() {
    var listAuthors = new ListAuthors(authorRepository);

    Exception exception = assertThrows(
      IllegalArgumentException.class,
      () -> listAuthors.execute(VALID_PAGE_NUMBER, INVALID_NUMBER_OF_RESULTS_PER_PAGE)
    );

    assertEquals("numberOfResultsPerPage invalid", exception.getMessage());
  }

  @Test
  void givenThatNoExistsAuthors_whenExecute_thenShouldReturnAEmptyList() {
    when(authorRepository.findAll(any(), any())).thenReturn(Collections.emptyList());

    List<AuthorOutput> authors =
      new ListAuthors(authorRepository).execute(VALID_PAGE_NUMBER, VALID_NUMBER_OF_RESULTS_PER_PAGE);

    assertTrue(authors.isEmpty());
  }

  @Test
  void givenThatExistsAuthors_whenExecute_thenShouldReturnAEmptyList() {
    when(authorRepository.findAll(any(), any())).thenReturn(buildListOfAuthors());

    List<AuthorOutput> authors =
      new ListAuthors(authorRepository).execute(VALID_PAGE_NUMBER, VALID_NUMBER_OF_RESULTS_PER_PAGE);

    assertEquals(2, authors.size());
  }

  private List<Author> buildListOfAuthors() {
    var uncleBob = Author.load(1L, "Robert C. Martin");
    var davidThomas = Author.load(2L, "David Thomas");

    return Arrays.asList(uncleBob, davidThomas);
  }
}
