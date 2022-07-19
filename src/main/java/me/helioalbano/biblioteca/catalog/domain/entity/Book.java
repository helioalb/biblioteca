package me.helioalbano.biblioteca.catalog.domain.entity;

import me.helioalbano.biblioteca.catalog.domain.valueobject.Title;

public class Book {
    private Title title;

    public Book(Title title) {
        setTitle(title);
    }

    private void setTitle(Title title) {
        if (title == null) {
            throw new IllegalArgumentException("book.title.null");
        }
        this.title = title;
    }

    public Title getTitle() {
        return title;
    }
}
