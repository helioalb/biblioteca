package me.helioalbano.biblioteca.catalog.usecase.book;

import me.helioalbano.biblioteca.catalog.domain.book.Book;
import me.helioalbano.biblioteca.catalog.repository.BookRepository;
import me.helioalbano.biblioteca.catalog.usecase.book.dto.CreateBookInput;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class CreateBookTest {
  @Test
  void addAnInexistentBook() {
    BookRepository repository = mock(BookRepository.class);
    var input = new CreateBookInput("O Programador Pragmático");

    when(repository.save(any(Book.class))).thenReturn(mock(Book.class));
    new CreateBook(repository).execute(input);

    verify(repository, times(1)).save(any(Book.class));
  }
}
