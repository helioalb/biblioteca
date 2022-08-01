package me.helioalbano.biblioteca.catalog.usecase.book.create.impl;

import me.helioalbano.biblioteca.catalog.domain.entity.Book;
import me.helioalbano.biblioteca.catalog.domain.repository.BookRepository;
import me.helioalbano.biblioteca.catalog.domain.valueobject.Title;
import me.helioalbano.biblioteca.catalog.usecase.book.create.Create;
import me.helioalbano.biblioteca.catalog.usecase.book.create.dto.CreateBookInput;

public class CreateImpl implements Create {

    private final BookRepository repository;

    public CreateImpl(final BookRepository repository) {
        this.repository = repository;
    }

    public Long execute(CreateBookInput createBookInput) {
        Book book = new Book(null, new Title(createBookInput.getTitle()));
        return repository.create(book).getId();
    }

}
