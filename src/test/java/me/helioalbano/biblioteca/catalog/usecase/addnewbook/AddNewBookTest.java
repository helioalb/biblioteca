package me.helioalbano.biblioteca.catalog.usecase.addnewbook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;

import me.helioalbano.biblioteca.catalog.domain.repository.BookRepository;

public class AddNewBookTest {
    @Test
    void addAnInexistentBook() {
        BookRepository repository = mock(BookRepository.class);
        AddNewBook addNewBook = new AddNewBookImpl(repository);
        Input input = new Input("O Programador Pragmático");
        Output output = addNewBook.execute(input);
        assertEquals("O Programador Pragmático", output.getTitle());
    }
}
