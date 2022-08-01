package me.helioalbano.biblioteca.catalog.infra.controller.dto;

import lombok.Data;
import me.helioalbano.biblioteca.catalog.usecase.book.create.dto.CreateBookInput;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CreateBookRequest {
    @NotBlank(message = "The book title must be informed")
    @Size(min = 3, max = 50)
    private String title;

    public CreateBookInput toUseCaseInput() {
        var useCaseInput = new CreateBookInput();
        useCaseInput.setTitle(title);
        return  useCaseInput;
    }
}
