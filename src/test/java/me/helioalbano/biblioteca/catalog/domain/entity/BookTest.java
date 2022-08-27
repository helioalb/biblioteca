package me.helioalbano.biblioteca.catalog.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import me.helioalbano.biblioteca.catalog.domain.exceptions.TitleFormatException;

public class BookTest {

    @Test
    void emptyTitle() {
        Exception e = assertThrows(TitleFormatException.class,
            () -> Book.load(1L, null));
        assertEquals("title.null", e.getMessage());
    }

    @Test
    void nonEmptyTitle() {
        var book = Book.load(1L, "O Programador Pragmático");

        assertEquals("O Programador Pragmático", book.getTitle().toString());
    }
}
