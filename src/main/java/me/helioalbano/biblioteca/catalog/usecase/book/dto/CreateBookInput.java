package me.helioalbano.biblioteca.catalog.usecase.book.dto;

import java.util.Set;

public record CreateBookInput(String title, Set<Long> authorsIds) {
}
