package me.helioalbano.biblioteca.catalog.domain.book;

import me.helioalbano.biblioteca.catalog.domain.author.Author;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

  @Test
  void emptyTitle() {
    Exception e = assertThrows(IllegalArgumentException.class,
        () -> Book.load(1L, null));
    assertEquals("title.null", e.getMessage());
  }

  @Test
  void nonEmptyTitle() {
    var book = Book.load(1L, "O Programador Pragmático");

    assertEquals("O Programador Pragmático", book.getTitle().toString());
  }

  @Test
  void givenAnAuthor_whenCallAddAuthor_thenItShouldBeAddedToBook() {
    var author = Author.create("Vaughn Vernon");
    var book = Book.create("Implementing Domain-Driven Design");

    assertEquals(0, book.getAuthors().count());

    book.addAuthor(author);
    assertEquals(1, book.getAuthors().count());
  }
}
