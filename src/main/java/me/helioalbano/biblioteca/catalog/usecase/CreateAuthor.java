package me.helioalbano.biblioteca.catalog.usecase;

import me.helioalbano.biblioteca.catalog.domain.entity.Author;
import me.helioalbano.biblioteca.catalog.domain.repository.AuthorRepository;
import me.helioalbano.biblioteca.catalog.domain.valueobject.Name;
import me.helioalbano.biblioteca.catalog.usecase.dto.CreateAuthorInput;

public class CreateAuthor {
    private final AuthorRepository repository;

    public CreateAuthor(final AuthorRepository repository) {
        this.repository = repository;
    }

    public Long execute(CreateAuthorInput input) {
        var author = new Author(null, new Name(input.getName()));
        return repository.create(author).getId();
    }
}
