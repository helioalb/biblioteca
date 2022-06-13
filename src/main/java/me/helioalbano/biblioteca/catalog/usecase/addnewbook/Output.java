package me.helioalbano.biblioteca.catalog.usecase.addnewbook;

import me.helioalbano.biblioteca.catalog.domain.entity.Book;

public class Output {
    private String title;

    public Output(Book book) {
        this.title = book.getTitle().toString();
    }

    public String getTitle() {
        return title;
    }
}
