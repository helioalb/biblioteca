package me.helioalbano.biblioteca.catalog.usecase;

import me.helioalbano.biblioteca.catalog.domain.entity.author.Author;
import me.helioalbano.biblioteca.catalog.domain.repository.AuthorRepository;
import me.helioalbano.biblioteca.catalog.usecase.dto.AuthorOutput;

import java.util.List;

public class ListAuthors {
  private final AuthorRepository repository;

  public ListAuthors(AuthorRepository repository) {
    this.repository = repository;
  }

  public List<AuthorOutput> execute(Integer pageNumber, Integer numberOfResultsPerPage) {
    validate(pageNumber, numberOfResultsPerPage);

    return repository.findAll(pageNumber, numberOfResultsPerPage)
      .stream().map(this::buildAuthorOutput).toList();
  }

  private void validate(Integer pageNumber, Integer numberOfResultsPerPage) {
    if (pageNumber < 0)
      throw new IllegalArgumentException("pageNumber invalid");

    if (numberOfResultsPerPage < 1)
      throw new IllegalArgumentException("numberOfResultsPerPage invalid");
  }

  private AuthorOutput buildAuthorOutput(Author author) {
    return new AuthorOutput(author.getId(), author.getName());
  }
}