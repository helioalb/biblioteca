package me.helioalbano.biblioteca.catalog.usecase.book;

import me.helioalbano.biblioteca.catalog.domain.book.Book;
import me.helioalbano.biblioteca.catalog.repository.AuthorRepository;
import me.helioalbano.biblioteca.catalog.repository.BookRepository;
import me.helioalbano.biblioteca.catalog.usecase.book.dto.CreateBookInput;

import java.util.Set;

public class CreateBook {

  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;

  public CreateBook(final BookRepository bookRepository,
                    final AuthorRepository authorRepository) {
    this.bookRepository = bookRepository;
    this.authorRepository = authorRepository;
  }

  public Long execute(CreateBookInput input) {
    Book book = Book.create(input.title());
    book = addAuthorsToBook(input.authorsIds(), book);
    return bookRepository.save(book).getId();
  }

  private Book addAuthorsToBook(Set<Long> authorsIds, Book book) {
    if (authorsIds != null)
      for (Long authorId : authorsIds)
        book.addAuthor(authorRepository.findById(authorId));

    return book;
  }
}
