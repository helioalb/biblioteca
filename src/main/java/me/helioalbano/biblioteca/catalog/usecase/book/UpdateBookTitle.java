package me.helioalbano.biblioteca.catalog.usecase.book;

import me.helioalbano.biblioteca.catalog.domain.book.Title;
import me.helioalbano.biblioteca.catalog.repository.BookRepository;

public class UpdateBookTitle {

  private final BookRepository repository;

  public UpdateBookTitle(BookRepository repository) {
    this.repository = repository;
  }

  public String execute(Long id, String newBookTitle) {
    var book = repository.findById(id);
    book.setTitle(new Title(newBookTitle));
    return repository.update(book).getTitle().toString();
  }
}
