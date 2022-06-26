package me.helioalbano.biblioteca.catalog.usecase.addnewbook;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import me.helioalbano.biblioteca.catalog.domain.entity.Book;
import me.helioalbano.biblioteca.catalog.usecase.addnewbook.dto.AddNewBookInput;
import me.helioalbano.biblioteca.catalog.usecase.addnewbook.impl.AddNewBookImpl;
import org.junit.jupiter.api.Test;

import me.helioalbano.biblioteca.catalog.domain.repository.BookRepository;

public class AddNewBookTest {
    @Test
    void addAnInexistentBook() {
        BookRepository repository = mock(BookRepository.class);
        var input = new AddNewBookInput("O Programador Pragmático");
        new AddNewBookImpl(repository).execute(input);
        verify(repository, times(1)).create(any(Book.class));
    }
}
