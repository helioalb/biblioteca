package me.helioalbano.biblioteca.catalog.usecase.book;

import me.helioalbano.biblioteca.catalog.domain.book.Book;
import me.helioalbano.biblioteca.catalog.repository.BookRepository;
import me.helioalbano.biblioteca.catalog.usecase.book.dto.BookOutput;

import java.util.List;

public class ListBooks {
  private final BookRepository repository;

  public ListBooks(BookRepository repository) {
    this.repository = repository;
  }

  public List<BookOutput> execute(Integer pageNumber, Integer numberOfResultsPerPage) {
    validate(pageNumber, numberOfResultsPerPage);

    return repository.findAll(pageNumber, numberOfResultsPerPage)
      .stream().map(this::buildBookOutput).toList();
  }

  private void validate(Integer pageNumber, Integer numberOfResultsPerPage) {
    if (pageNumber < 0)
      throw new IllegalArgumentException("pageNumber invalid");

    if (numberOfResultsPerPage < 1)
      throw new IllegalArgumentException("numberOfResultsPerPage invalid");
  }

  private BookOutput buildBookOutput(Book book) {
    return new BookOutput(book.getId(), book.getTitle().toString());
  }
}
