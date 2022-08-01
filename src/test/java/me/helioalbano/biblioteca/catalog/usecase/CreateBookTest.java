package me.helioalbano.biblioteca.catalog.usecase;

import me.helioalbano.biblioteca.catalog.domain.entity.Book;
import me.helioalbano.biblioteca.catalog.domain.repository.BookRepository;
import me.helioalbano.biblioteca.catalog.usecase.dto.CreateBookInput;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class CreateBookTest {
    @Test
    void addAnInexistentBook() {
        BookRepository repository = mock(BookRepository.class);
        var input = new CreateBookInput();
        input.setTitle("O Programador Pragmático");

        when(repository.create(any(Book.class))).thenReturn(mock(Book.class));
        new CreateBook(repository).execute(input);

        verify(repository, times(1)).create(any(Book.class));
    }
}
