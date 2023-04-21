package me.helioalbano.biblioteca.catalog.usecase.exceptions;

public class BookNotFoundException extends RuntimeException {
  public BookNotFoundException() {
    super("book.not.found");
  }
}
