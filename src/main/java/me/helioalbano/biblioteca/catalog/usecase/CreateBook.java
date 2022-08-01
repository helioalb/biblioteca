package me.helioalbano.biblioteca.catalog.usecase;

import me.helioalbano.biblioteca.catalog.domain.entity.Book;
import me.helioalbano.biblioteca.catalog.domain.repository.BookRepository;
import me.helioalbano.biblioteca.catalog.domain.valueobject.Title;
import me.helioalbano.biblioteca.catalog.usecase.dto.CreateBookInput;

public class CreateBook {

    private final BookRepository repository;

    public CreateBook(final BookRepository repository) {
        this.repository = repository;
    }

    public Long execute(CreateBookInput createBookInput) {
        var title = new Title(createBookInput.getTitle());
        Book book = new Book(null, title);

        return repository.create(book).getId();
    }

}
