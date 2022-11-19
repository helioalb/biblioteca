package me.helioalbano.biblioteca.catalog.usecase.dto;

import lombok.Getter;

@Getter
public class CreateBookInput {
  private final String title;

  public CreateBookInput(final String title) {
    this.title = title;
  }
}
