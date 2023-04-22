package me.helioalbano.biblioteca.catalog.domain.book;

import java.time.LocalDateTime;

public class Book {

  private final Long id;
  private Title title;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public static Book create(String title) {
    return new Book(new Title(title));
  }

  public static Book load(long id, String title) {
    return new Book(id, new Title(title));
  }

  public static Book load(long id, String title, LocalDateTime createdAt, LocalDateTime updatedAt) {
    return new Book(id, new Title(title), createdAt, updatedAt);
  }

  private Book(Title title) {
    this.id = null;
    setTitle(title);
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
}
