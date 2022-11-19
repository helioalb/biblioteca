package me.helioalbano.biblioteca.catalog.domain.repository;

import me.helioalbano.biblioteca.catalog.domain.entity.author.Author;

public interface AuthorRepository {
  Author create(Author author);
}
