package me.helioalbano.biblioteca.catalog.domain.repository;

import me.helioalbano.biblioteca.catalog.domain.entity.author.Author;

import java.util.List;

public interface AuthorRepository {
  Author create(Author author);
  Author findById(Long id);
  List<Author> findAll(Integer pageNumber, Integer numberOfResultsPerPage);
}
