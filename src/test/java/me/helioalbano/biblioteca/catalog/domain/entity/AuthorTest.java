package me.helioalbano.biblioteca.catalog.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import me.helioalbano.biblioteca.catalog.domain.exceptions.InvalidAuthorNameException;

class AuthorTest {

    @Test
    void create_shouldBeOk() {
        var author = Author.create("Gayle Laakmann McDowell");
        assertEquals("Gayle Laakmann McDowell", author.getName().toString());
    }

    @Test
    void createWithEmptyName_shouldThrowAnException() {
        Exception e =
            assertThrows(InvalidAuthorNameException.class, () -> Author.load(null, null));
        assertEquals("name.null", e.getMessage());
    }
}
