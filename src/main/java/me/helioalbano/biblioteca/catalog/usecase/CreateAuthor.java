package me.helioalbano.biblioteca.catalog.usecase;

import me.helioalbano.biblioteca.catalog.domain.entity.author.Author;
import me.helioalbano.biblioteca.catalog.domain.repository.AuthorRepository;
import me.helioalbano.biblioteca.catalog.usecase.dto.CreateAuthorInput;

public class CreateAuthor {
  private final AuthorRepository repository;

  public CreateAuthor(final AuthorRepository repository) {
    this.repository = repository;
  }

  public Long execute(CreateAuthorInput input) {
    var author = Author.create(input.getName());
    return repository.create(author).getId();
  }
}
