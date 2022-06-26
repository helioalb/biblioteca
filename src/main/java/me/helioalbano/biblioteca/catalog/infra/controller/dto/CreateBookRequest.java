package me.helioalbano.biblioteca.catalog.infra.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateBookRequest {
    @NotNull(message = "The book title must be informed")
    private String title;
}
