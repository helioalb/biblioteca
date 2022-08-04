package me.helioalbano.biblioteca.catalog.domain.entity;

import me.helioalbano.biblioteca.catalog.domain.valueobject.Name;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuthorTest {

    @Test
    void constructorWithName_shouldBeOk() {
        var name = new Name("Gayle Laakmann McDowell");
        var author = new Author(null, name);
        assertEquals("Gayle Laakmann McDowell", author.getName().toString());
    }

    @Test
    void constructorWithEmptyName_shouldThrowAnException() {
        Exception e =
            assertThrows(IllegalArgumentException.class, () -> new Author(null, null));
        assertEquals("author.name.null", e.getMessage());
    }
}
