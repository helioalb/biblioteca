package me.helioalbano.biblioteca.catalog.domain.book;

import me.helioalbano.biblioteca.catalog.domain.author.Author;

import java.time.LocalDateTime;

public class Book {

  private final Long id;
  private Title title;
  private Authors authors;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public static Book create(String title) {
    return new Book(new Title(title), new Authors());
  }

  public static Book load(long id, String title) {
    return new Book(id, new Title(title));
  }

  public static Book load(long id, String title, LocalDateTime createdAt, LocalDateTime updatedAt) {
    return new Book(id, new Title(title), createdAt, updatedAt);
  }

  private Book(Title title, Authors authors) {
    this.id = null;
    setTitle(title);
    this.authors = authors;
  }

  private Book(Long id, Title title) {
    this.id = id;
    setTitle(title);
  }

  private Book(Long id, Title title, LocalDateTime createdAt, LocalDateTime updatedAt) {
    this.id = id;
    setTitle(title);
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public void setTitle(Title title) {
    if (title == null)
      throw new IllegalArgumentException("book.title.null");

    this.title = title;
  }

  public Long getId() {
    return id;
  }

  public Title getTitle() {
    return title;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void addAuthor(Author author) {
    authors.add(author);
  }

  public Authors getAuthors() {
    return authors;
  }
}
