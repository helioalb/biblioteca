package me.helioalbano.biblioteca.catalog.adapter.database.memory;

import me.helioalbano.biblioteca.catalog.domain.book.Book;
import me.helioalbano.biblioteca.catalog.repository.BookRepository;

import java.util.HashMap;
import java.util.Map;

public class BookRepositoryInMemory implements BookRepository {
  private final Map<String, Book> books;

  public BookRepositoryInMemory() {
    books = new HashMap<>();
  }

  @Override
  public Book create(Book book) {
    books.put(book.getTitle().toString(), book);
    return book;
  }
}
