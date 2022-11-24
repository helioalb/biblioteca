package me.helioalbano.biblioteca.catalog.domain.book;

import me.helioalbano.biblioteca.catalog.domain.book.Title;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TitleTest {
  @Test
  void testPrintOfTitle() {
    var title = new Title("Implementing Domain-Driven Design");
    assertEquals("Implementing Domain-Driven Design", title.toString());
  }
  @Test
  void testIfNull() {
    Exception e = assertThrows(IllegalArgumentException.class,
        () -> new Title(null));
    assertEquals("title.null", e.getMessage());
  }

  @Test
  void testIfLengthIsGreaterThan50() {
    var title = "My Grandmother Asked Me to Tell You She’s Sorry. Vol II";
    Exception e = assertThrows(IllegalArgumentException.class,
        () -> new Title(title));
    assertEquals("title.greater.than.50", e.getMessage());
  }

  @Test
  void testIfLengthIs0() {
    Exception e = assertThrows(IllegalArgumentException.class,
        () -> new Title(""));
    assertEquals("title.length.zero", e.getMessage());
  }

}
