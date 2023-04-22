package me.helioalbano.biblioteca.catalog.usecase.book;

import me.helioalbano.biblioteca.catalog.domain.book.Book;
import me.helioalbano.biblioteca.catalog.repository.BookRepository;
import me.helioalbano.biblioteca.catalog.usecase.book.dto.CreateBookInput;

public class CreateBook {

  private final BookRepository repository;

  public CreateBook(final BookRepository repository) {
    this.repository = repository;
  }

  public Long execute(CreateBookInput createBookInput) {
    Book book = Book.create(createBookInput.title());
    return repository.create(book).getId();
  }

}
