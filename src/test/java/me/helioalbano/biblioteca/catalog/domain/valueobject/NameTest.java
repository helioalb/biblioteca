package me.helioalbano.biblioteca.catalog.domain.valueobject;

import me.helioalbano.biblioteca.catalog.domain.exceptions.InvalidAuthorNameException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        Exception e = assertThrows(InvalidAuthorNameException.class, () -> new Name("Zé"));
        assertEquals("name.length.less.than.two", e.getMessage());
    }

    @Test
    void nullName_shouldThrowException() {
        Exception e = assertThrows(InvalidAuthorNameException.class, () -> new Name(null));
        assertEquals("name.null", e.getMessage());
    }
}
