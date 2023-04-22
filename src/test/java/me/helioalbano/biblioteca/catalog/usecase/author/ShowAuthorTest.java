package me.helioalbano.biblioteca.catalog.usecase.author;

import me.helioalbano.biblioteca.catalog.domain.author.Author;
import me.helioalbano.biblioteca.catalog.repository.AuthorRepository;
import me.helioalbano.biblioteca.catalog.usecase.author.dto.AuthorOutput;
import me.helioalbano.biblioteca.catalog.usecase.exceptions.AuthorNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ShowAuthorTest {
  private AuthorRepository authorRepository;
  private final static Long VALID_AUTHOR_ID = 1L;
  private final static Long INVALID_AUTHOR_ID = 1000L;

  @BeforeEach
  void setup() {
    authorRepository = mock(AuthorRepository.class);
  }

  @Test
  void givenAnInvalidAuthorId_whenExecute_thenShouldRaiseAnException() {
    var showAuthor = new ShowAuthor(authorRepository);
    when(authorRepository.findById(INVALID_AUTHOR_ID)).thenThrow(new AuthorNotFoundException());

    Exception exception =
      assertThrows(AuthorNotFoundException.class, () -> showAuthor.execute(INVALID_AUTHOR_ID));

    assertNotNull(exception);
  }

  @Test
  void givenAValidAuthorId_whenExecute_thenShouldReturnAnAuthorOutput(){
    when(authorRepository.findById(VALID_AUTHOR_ID)).thenReturn(buildAuthor());

    AuthorOutput authorOutput = new ShowAuthor(authorRepository).execute(VALID_AUTHOR_ID);

    assertEquals(VALID_AUTHOR_ID, authorOutput.id());
    assertEquals("Author name", authorOutput.name());
  }

  private Author buildAuthor() {
    return Author.load(VALID_AUTHOR_ID, "Author name");
  }
}
