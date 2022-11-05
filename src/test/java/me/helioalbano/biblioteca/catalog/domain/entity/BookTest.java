package me.helioalbano.biblioteca.catalog.domain.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
}
