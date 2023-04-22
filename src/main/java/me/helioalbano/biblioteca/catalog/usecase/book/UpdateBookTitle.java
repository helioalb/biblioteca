package me.helioalbano.biblioteca.catalog.usecase.book;

import me.helioalbano.biblioteca.catalog.domain.book.Title;
import me.helioalbano.biblioteca.catalog.repository.BookRepository;
import me.helioalbano.biblioteca.catalog.usecase.book.dto.BookOutput;

public class UpdateBookTitle {

  private final BookRepository repository;

  public UpdateBookTitle(BookRepository repository) {
    this.repository = repository;
  }

  public BookOutput execute(Long id, String newBookTitle) {
    var book = repository.findById(id);
    book.setTitle(new Title(newBookTitle));

    var updatedBook = repository.save(book);
    return new BookOutput(updatedBook.getId(), updatedBook.getTitle().toString());
  }
}
