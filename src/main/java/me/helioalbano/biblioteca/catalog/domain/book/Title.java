package me.helioalbano.biblioteca.catalog.domain.book;

public class Title {
  private String value;

  public Title(String title) {
    this.setTitle(title);
  }

  private void setTitle(String title) {
    if (title == null)
      throw new IllegalArgumentException("title.null");

    if (title.length() >= 50)
      throw new IllegalArgumentException("title.greater.than.50");

    if (title.length() <= 0)
      throw new IllegalArgumentException("title.length.zero");

    this.value = title;
  }

  @Override
  public String toString() {
    return this.value;
  }
}
