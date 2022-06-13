package me.helioalbano.biblioteca.catalog.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import me.helioalbano.biblioteca.catalog.domain.valueobject.Title;

public class BookTest {

    @Test
    void emptyTitle() {
        Exception e = assertThrows(IllegalArgumentException.class,
            () -> new Book(null));
        assertEquals("book.title.null", e.getMessage());
    }

    @Test
    void nonEmptyTitle() {
        var book = new Book(new Title("O Programador Pragmático"));
        assertEquals("O Programador Pragmático", book.getTitle().toString());
    }
}
