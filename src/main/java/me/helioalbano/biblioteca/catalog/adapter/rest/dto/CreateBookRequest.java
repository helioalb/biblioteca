package me.helioalbano.biblioteca.catalog.adapter.rest.dto;

import lombok.Data;
import me.helioalbano.biblioteca.catalog.usecase.book.dto.CreateBookInput;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CreateBookRequest {
  @NotBlank(message = "The book title must be informed")
  @Size(min = 3, max = 50)
  private String title;

  public CreateBookInput toUseCaseInput() {
    return new CreateBookInput(title);
  }
}
