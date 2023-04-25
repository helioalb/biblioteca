package me.helioalbano.biblioteca.catalog.domain.book;

import me.helioalbano.biblioteca.catalog.domain.author.Author;

import java.util.HashSet;
import java.util.Set;

public class Authors {
  private Set<Author> authors;

  public Authors() {
    authors = new HashSet<>();
  }

  public void add(Author author) {
    authors.add(author);
  }

  public int count() {
    return authors.size();
  }
}
