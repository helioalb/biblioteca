package me.helioalbano.biblioteca.catalog.usecase.dto;

import lombok.Getter;

@Getter
public class CreateAuthorInput {
  private final String name;

  public CreateAuthorInput(final String name) {
    this.name = name;
  }

}
