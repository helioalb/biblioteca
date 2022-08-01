package me.helioalbano.biblioteca.catalog.domain.entity;

import me.helioalbano.biblioteca.catalog.domain.valueobject.Title;

public class Book {

    private final Long id;
    private Title title;

    public Book(Long id, Title title) {
        this.id = id;
        setTitle(title);
    }

    private void setTitle(Title title) {
        if (title == null) {
            throw new IllegalArgumentException("book.title.null");
        }
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public Title getTitle() {
        return title;
    }
}
