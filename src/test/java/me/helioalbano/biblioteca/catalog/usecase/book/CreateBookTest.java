package me.helioalbano.biblioteca.catalog.usecase.book;

import me.helioalbano.biblioteca.catalog.domain.author.Author;
import me.helioalbano.biblioteca.catalog.domain.book.Book;
import me.helioalbano.biblioteca.catalog.repository.AuthorRepository;
import me.helioalbano.biblioteca.catalog.repository.BookRepository;
import me.helioalbano.biblioteca.catalog.usecase.book.dto.CreateBookInput;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.mockito.Mockito.*;

public class CreateBookTest {
  @Test
  void addAnInexistentBook() {
    BookRepository bookRepository = mock(BookRepository.class);
    AuthorRepository authorRepository = mock(AuthorRepository.class);
    var input = new CreateBookInput("O Programador Pragmático", null);

    when(bookRepository.save(any(Book.class))).thenReturn(mock(Book.class));
    new CreateBook(bookRepository, authorRepository).execute(input);

    verify(bookRepository, times(1)).save(any(Book.class));
  }

  @Test
  void givenBookWithOneAuthor_whenExecute_thenABookWithOneAuthorShouldBeCreated() {
    BookRepository bookRepository = mock(BookRepository.class);
    AuthorRepository authorRepository = mock(AuthorRepository.class);
    var createBook = new CreateBook(bookRepository, authorRepository);
    var input = new CreateBookInput("Implementing Domain-Driven Design", Set.of(1L));

    when(authorRepository.findById(1L)).thenReturn(Author.load(1L, "Vaughn Vernon"));
    when(bookRepository.save(any(Book.class))).thenReturn(mock(Book.class));
    createBook.execute(input);

    verify(authorRepository, times(1)).findById(any(Long.class));
    verify(bookRepository, times(1)).save(any(Book.class));
  }

  @Test
  void givenBookWithForAuthors_whenExecute_thenABookWithOneAuthorShouldBeCreated() {
    BookRepository bookRepository = mock(BookRepository.class);
    AuthorRepository authorRepository = mock(AuthorRepository.class);
    var createBook = new CreateBook(bookRepository, authorRepository);
    var input = new CreateBookInput("Design Patterns", Set.of(1L, 2L, 3L, 4L));

    when(authorRepository.findById(1L)).thenReturn(Author.load(1L, "Erich Gama"));
    when(authorRepository.findById(1L)).thenReturn(Author.load(2L, "Richard Helm"));
    when(authorRepository.findById(1L)).thenReturn(Author.load(3L, "Ralph Johnson"));
    when(authorRepository.findById(1L)).thenReturn(Author.load(4L, "John Vlissides"));
    when(bookRepository.save(any(Book.class))).thenReturn(mock(Book.class));
    createBook.execute(input);

    verify(authorRepository, times(4)).findById(any(Long.class));
    verify(bookRepository, times(1)).save(any(Book.class));
  }
}
