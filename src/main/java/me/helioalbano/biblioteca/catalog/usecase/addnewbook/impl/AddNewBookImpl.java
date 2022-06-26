package me.helioalbano.biblioteca.catalog.usecase.addnewbook;

import me.helioalbano.biblioteca.catalog.domain.entity.Book;
import me.helioalbano.biblioteca.catalog.domain.repository.BookRepository;
import me.helioalbano.biblioteca.catalog.domain.valueobject.Title;
import me.helioalbano.biblioteca.catalog.usecase.addnewbook.dto.Input;

public class AddNewBookImpl implements AddNewBook {

    private BookRepository repository;

    public AddNewBookImpl(final BookRepository repository) {
        this.repository = repository;
    }

    public void execute(Input input) {
        Book book = new Book(new Title(input.getTitle()));
        repository.create(book);
    }

}
