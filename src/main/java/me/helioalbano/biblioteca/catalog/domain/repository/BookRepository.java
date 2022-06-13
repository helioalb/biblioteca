package me.helioalbano.biblioteca.catalog.domain.repository;

import me.helioalbano.biblioteca.catalog.domain.entity.Book;

public interface BookRepository {
    void create(Book book);
}
