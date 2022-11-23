package me.helioalbano.biblioteca.catalog.usecase.exceptions;

public class AuthorNotFoundException extends RuntimeException {
  public AuthorNotFoundException() {
    super("author.not.found");
  }
}
