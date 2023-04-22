package me.helioalbano.biblioteca.catalog.usecase.book;

import me.helioalbano.biblioteca.catalog.repository.BookRepository;
import me.helioalbano.biblioteca.catalog.usecase.book.dto.BookOutput;

public class ShowBook {
  private final BookRepository repository;

  public ShowBook(BookRepository repository) {
    this.repository = repository;
  }

  public BookOutput execute(Long id) {
    var book = repository.findById(id);
    return new BookOutput(book.getId(), book.getTitle().toString());
  }
}
