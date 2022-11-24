package me.helioalbano.biblioteca.catalog.usecase;

import me.helioalbano.biblioteca.catalog.repository.AuthorRepository;
import me.helioalbano.biblioteca.catalog.usecase.dto.AuthorOutput;

public class ShowAuthor {
  private final AuthorRepository repository;

  public ShowAuthor(final AuthorRepository repository) {
    this.repository = repository;
  }

  public AuthorOutput execute(Long id) {
    var author = repository.findById(id);
    return new AuthorOutput(author.getId(), author.getName());
  }
}
