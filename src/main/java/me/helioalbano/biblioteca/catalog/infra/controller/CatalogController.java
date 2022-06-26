package me.helioalbano.biblioteca.catalog.infra.controller;

import me.helioalbano.biblioteca.catalog.infra.controller.dto.CreateBookRequest;
import me.helioalbano.biblioteca.catalog.infra.controller.dto.CreateBookResponse;
import me.helioalbano.biblioteca.catalog.usecase.addnewbook.AddNewBook;
import me.helioalbano.biblioteca.catalog.usecase.addnewbook.dto.AddNewBookInput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("catalog")
public class CatalogController {
    private AddNewBook addNewBook;

    public CatalogController(AddNewBook addNewBook) {
        this.addNewBook = addNewBook;
    }

    @PostMapping(path = "books")
    public ResponseEntity<CreateBookResponse> createBook(@RequestBody CreateBookRequest book) {
        var input = new AddNewBookInput(book.getTitle());
        addNewBook.execute(input);
        return ResponseEntity.ok(new CreateBookResponse(input.getTitle()));
    }
}
