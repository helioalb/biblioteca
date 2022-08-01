package me.helioalbano.biblioteca.catalog.usecase.book.create;

import me.helioalbano.biblioteca.catalog.usecase.book.create.dto.CreateBookInput;

public interface Create {
    Long execute(CreateBookInput createBookInput);
}
