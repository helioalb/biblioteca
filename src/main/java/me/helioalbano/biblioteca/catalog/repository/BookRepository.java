package me.helioalbano.biblioteca.catalog.repository;

import me.helioalbano.biblioteca.catalog.domain.book.Book;

import java.util.List;

public interface BookRepository {
  Book save(Book book);
  List<Book> findAll(Integer pageNumber, Integer numberOfResultsPerPage);
  Book findById(Long id);
}
