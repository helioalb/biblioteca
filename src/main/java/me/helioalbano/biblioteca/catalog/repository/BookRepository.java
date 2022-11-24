package me.helioalbano.biblioteca.catalog.repository;

import me.helioalbano.biblioteca.catalog.domain.book.Book;

public interface BookRepository {
  Book create(Book book);
}
