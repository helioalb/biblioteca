package me.helioalbano.biblioteca.catalog.domain.book;

public class Book {

  private final Long id;
  private Title title;

  public static Book create(String title) {
    return new Book(new Title(title));
  }

  public static Book load(long id, String title) {
    return new Book(id, new Title(title));
  }

  private Book(Title title) {
    this.id = null;
    setTitle(title);
  }

  private Book(Long id, Title title) {
    this.id = id;
    setTitle(title);
  }

  private void setTitle(Title title) {
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

}
