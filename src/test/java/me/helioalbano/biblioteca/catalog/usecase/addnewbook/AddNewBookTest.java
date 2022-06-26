package me.helioalbano.biblioteca.catalog.usecase.addnewbook;

import me.helioalbano.biblioteca.catalog.domain.entity.Book;
import me.helioalbano.biblioteca.catalog.domain.repository.BookRepository;
import me.helioalbano.biblioteca.catalog.usecase.addnewbook.dto.AddNewBookInput;
import me.helioalbano.biblioteca.catalog.usecase.addnewbook.impl.AddNewBookImpl;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class AddNewBookTest {
    @Test
    void addAnInexistentBook() {
        BookRepository repository = mock(BookRepository.class);
        var input = new AddNewBookInput();
        input.setTitle("O Programador Pragmático");
        new AddNewBookImpl(repository).execute(input);
        verify(repository, times(1)).create(any(Book.class));
    }
}
