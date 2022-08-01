package me.helioalbano.biblioteca.catalog.domain.valueobject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import me.helioalbano.biblioteca.catalog.domain.exceptions.TitleFormatException;

public class TitleTest {
    @Test
    void testPrintOfTitle() {
        Title title = new Title("Implementing Domain-Driven Design");
        assertEquals("Implementing Domain-Driven Design", title.toString());
    }
    @Test
    void testIfNull() {
        Exception e = assertThrows(TitleFormatException.class, () -> new Title(null));
        assertEquals("title.null", e.getMessage());
    }

    @Test
    void testIfLengthIsGreaterThan50() {
        Exception e = assertThrows(TitleFormatException.class,
            () -> new Title("My Grandmother Asked Me to Tell You She’s Sorry. Vol II"));
        assertEquals("title.greather.than.50", e.getMessage());
    }

    @Test
    void testIfLengthIs0() {
        Exception e = assertThrows(TitleFormatException.class, () -> new Title(""));
        assertEquals("title.length.0", e.getMessage());
    }

}
