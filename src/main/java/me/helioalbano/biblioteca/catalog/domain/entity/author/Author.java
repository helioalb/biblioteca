package me.helioalbano.biblioteca.catalog.domain.entity.author;

import lombok.Getter;
import org.springframework.util.Assert;

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
        Assert.notNull(name, "author.name.null");
        this.name = name;
    }

    public String getName() {
        return this.name.toString();
    }
}
