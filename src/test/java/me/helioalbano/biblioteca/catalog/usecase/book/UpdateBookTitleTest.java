package me.helioalbano.biblioteca.catalog.usecase.book;

import me.helioalbano.biblioteca.catalog.domain.book.Book;
import me.helioalbano.biblioteca.catalog.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class UpdateBookTitleTest {
  private BookRepository repository;
  private static final Long BOOK_ID = 1L;
  private static final String OLD_BOOK_TITLE = "Domain-Driven Design";
  private static final String NEW_BOOK_TITLE = "Implementando Domain-Driven Design";

  @BeforeEach
  void setUp() {
    repository = Mockito.mock(BookRepository.class);
  }

  @Test
  void givenTitleToUpdate_whenExecute_thanReturnNewTitle() {
    var updateBookTitle = new UpdateBookTitle(repository);

    Mockito.when(repository.findById(BOOK_ID))
        .thenReturn(buildBookWithOldTitle());

    Mockito.when(repository.save(Mockito.any(Book.class)))
      .thenReturn(buildBookWithNewTitle());

    // Before
    var book = repository.findById(BOOK_ID);
    assertEquals(OLD_BOOK_TITLE, book.getTitle().toString());


    // After
    var newBookTitle = updateBookTitle.execute(BOOK_ID, NEW_BOOK_TITLE);
    assertEquals(NEW_BOOK_TITLE, newBookTitle.title());
  }

  private Book buildBookWithOldTitle() {
    return Book.load(BOOK_ID, OLD_BOOK_TITLE);
  }

  private Book buildBookWithNewTitle() {
    return Book.load(BOOK_ID, NEW_BOOK_TITLE);
  }
}
