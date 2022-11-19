package me.helioalbano.biblioteca.catalog.infra.controller.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;
import me.helioalbano.biblioteca.catalog.usecase.dto.CreateAuthorInput;

@Data
public class CreateAuthorRequest {
  @NotBlank(message = "The author name must be informed")
  @Size(min = 5, max = 150)
  private String name;

  public CreateAuthorInput toUseCaseInput() {
    return new CreateAuthorInput(name);
  }
}
