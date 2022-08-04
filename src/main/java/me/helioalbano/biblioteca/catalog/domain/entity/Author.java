package me.helioalbano.biblioteca.catalog.domain.entity;

import lombok.Getter;
import me.helioalbano.biblioteca.catalog.domain.valueobject.Name;

@Getter
public class Author {

    private final Long id;
    private Name name;

    public Author(final Long id, final Name name) {
        this.id = id;
        setName(name);
    }

    private void setName(Name name) {
        if (name == null) throw new IllegalArgumentException("author.name.null");
        this.name = name;
    }

}
