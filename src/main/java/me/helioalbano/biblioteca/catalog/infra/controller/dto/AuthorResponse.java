package me.helioalbano.biblioteca.catalog.infra.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorResponse {
  private Long id;
  private String name;

  public static AuthorResponse create(Long id, String name) {
    return new AuthorResponse(id, name);
  }
}
