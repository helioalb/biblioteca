package me.helioalbano.biblioteca.catalog.usecase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import me.helioalbano.biblioteca.catalog.domain.entity.Author;
import me.helioalbano.biblioteca.catalog.domain.repository.AuthorRepository;
import me.helioalbano.biblioteca.catalog.usecase.dto.CreateAuthorInput;

public class CreateAuthorTest {
    @Test
    void addANewAuthor() {
        var repository = mock(AuthorRepository.class);
        var input = new CreateAuthorInput("Helio Albano");
        when(repository.create(any(Author.class))).thenReturn(mock(Author.class));
        new CreateAuthor(repository).execute(input);
        verify(repository, times(1)).create(any(Author.class));
    }
}
