package me.helioalbano.biblioteca.catalog.domain.repository;

import me.helioalbano.biblioteca.catalog.domain.entity.book.Book;

public interface BookRepository {
    Book create(Book book);
}
