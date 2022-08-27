package me.helioalbano.biblioteca.catalog.domain.entity;

import lombok.Getter;
import me.helioalbano.biblioteca.catalog.domain.valueobject.Name;

@Getter
public class Author {

    private final Long id;
    private Name name;

    public static Author load(Long id, String fullName) {
        return new Author(id, new Name(fullName));
    }

    private Author(final Long id, final Name name) {
        this.id = id;
        setName(name);
    }

    public static Author create(String fullName) {
        return new Author(new Name(fullName));
    }

    private Author(final Name name) {
        this.id = null;
        setName(name);
    }

    private void setName(Name name) {
        if (name == null) throw new IllegalArgumentException("author.name.null");
        this.name = name;
    }

    public String getName() {
        return this.name.toString();
    }
}
