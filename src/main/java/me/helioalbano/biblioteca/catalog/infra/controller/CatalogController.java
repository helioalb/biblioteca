package me.helioalbano.biblioteca.catalog.infra.controller;

import java.net.URI;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.helioalbano.biblioteca.catalog.infra.controller.dto.CreateBookRequest;
import me.helioalbano.biblioteca.catalog.usecase.book.create.Create;

@RestController
@RequestMapping("catalog")
class CatalogController {
    private final Create create;

    public CatalogController(Create create) {
        this.create = create;
    }

    @PostMapping(path = "books")
    public ResponseEntity<Void> createBook(@Valid @RequestBody CreateBookRequest book) {
        var bookId = create.execute(book.toUseCaseInput());
        var location = URI.create("/catalog/books/" + bookId);
        return ResponseEntity.created(location).build();
    }
}
