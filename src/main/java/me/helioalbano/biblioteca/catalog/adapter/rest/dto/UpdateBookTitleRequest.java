package me.helioalbano.biblioteca.catalog.adapter.rest.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class UpdateBookTitleRequest {
  @NotBlank(message = "The book title must be informed")
  @Size(min = 3, max = 50)
  public String title;
}
