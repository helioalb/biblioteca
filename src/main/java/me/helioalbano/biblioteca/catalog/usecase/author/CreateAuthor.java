package me.helioalbano.biblioteca.catalog.usecase.author;

import me.helioalbano.biblioteca.catalog.domain.author.Author;
import me.helioalbano.biblioteca.catalog.repository.AuthorRepository;
import me.helioalbano.biblioteca.catalog.usecase.author.dto.CreateAuthorInput;

public class CreateAuthor {
  private final AuthorRepository repository;

  public CreateAuthor(final AuthorRepository repository) {
    this.repository = repository;
  }

  public Long execute(CreateAuthorInput input) {
    var author = Author.create(input.name());
    return repository.create(author).getId();
  }
}
