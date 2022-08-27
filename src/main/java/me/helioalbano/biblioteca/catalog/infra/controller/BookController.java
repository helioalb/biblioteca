package me.helioalbano.biblioteca.catalog.infra.controller;

import java.net.URI;

import javax.validation.Valid;

import me.helioalbano.biblioteca.catalog.usecase.CreateBook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.helioalbano.biblioteca.catalog.infra.controller.dto.CreateBookRequest;

@RestController
@RequestMapping("catalog")
class BookController {
    private final CreateBook createBook;

    public BookController(CreateBook createBook) {
        this.createBook = createBook;
    }

    @PostMapping(path = "books")
    public ResponseEntity<Void> create(@Valid @RequestBody CreateBookRequest book) {
        var bookId = createBook.execute(book.toUseCaseInput());
        return ResponseEntity.created(resourcePathTo(bookId)).build();
    }

    private URI resourcePathTo(Long bookId) {
        return URI.create("/catalog/books/" + bookId);
    }
}
