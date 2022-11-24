package me.helioalbano.biblioteca.catalog.domain.author;

import me.helioalbano.biblioteca.catalog.domain.author.Name;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NameTest {

  @Test
  void toString_shouldReturnFullName() {
    var name = new Name("Helio Albano de Oliveira");
    assertEquals("Helio Albano de Oliveira", name.toString());
  }

  @Test
  void getFirstName_shouldReturnFirstName() {
    var name = new Name("Helio Albano de Oliveira");
    assertEquals("Helio", name.getFirstName());
  }

  @Test
  void getLastName_shouldReturnLastName() {
    var name = new Name("Helio Albano de Oliveira");
    assertEquals("Oliveira", name.getLastName());
  }

  @Test
  void singleName_shouldThrowException() {
    Exception e = assertThrows(IllegalArgumentException.class,
        () -> new Name("Zé"));
    assertEquals("name.length.less.than.two.parts", e.getMessage());
  }

  @Test
  void nullName_shouldThrowException() {
    Exception e = assertThrows(IllegalArgumentException.class,
        () -> new Name(null));
    assertEquals("name.null", e.getMessage());
  }
}
