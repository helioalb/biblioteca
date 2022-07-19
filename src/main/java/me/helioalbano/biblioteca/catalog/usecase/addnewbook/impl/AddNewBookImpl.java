package me.helioalbano.biblioteca.catalog.usecase.addnewbook.impl;

import me.helioalbano.biblioteca.catalog.domain.entity.Book;
import me.helioalbano.biblioteca.catalog.domain.repository.BookRepository;
import me.helioalbano.biblioteca.catalog.domain.valueobject.Title;
import me.helioalbano.biblioteca.catalog.usecase.addnewbook.AddNewBook;
import me.helioalbano.biblioteca.catalog.usecase.addnewbook.dto.AddNewBookInput;

public class AddNewBookImpl implements AddNewBook {

    private BookRepository repository;

    public AddNewBookImpl(final BookRepository repository) {
        this.repository = repository;
    }

    public void execute(AddNewBookInput addNewBookInput) {
        Book book = new Book(new Title(addNewBookInput.getTitle()));
        repository.create(book);
    }

}
