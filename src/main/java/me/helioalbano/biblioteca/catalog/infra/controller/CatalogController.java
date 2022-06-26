package me.helioalbano.biblioteca.catalog.infra.controller;

import me.helioalbano.biblioteca.catalog.infra.controller.dto.CreateBookRequest;
import me.helioalbano.biblioteca.catalog.infra.controller.dto.CreateBookResponse;
import me.helioalbano.biblioteca.catalog.usecase.addnewbook.AddNewBook;
import me.helioalbano.biblioteca.catalog.usecase.addnewbook.dto.AddNewBookInput;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("catalog")
class CatalogController {
    private AddNewBook addNewBook;
    private ModelMapper modelMapper;

    public CatalogController(AddNewBook addNewBook, ModelMapper modelMapper) {
        this.addNewBook = addNewBook;
        this.modelMapper = modelMapper;
    }

    @PostMapping(path = "books")
    public ResponseEntity<CreateBookResponse> createBook(@RequestBody CreateBookRequest book) {
        var input = modelMapper.map(book, AddNewBookInput.class);
        addNewBook.execute(input);
        return ResponseEntity.ok(new CreateBookResponse(input.getTitle()));
    }
}
