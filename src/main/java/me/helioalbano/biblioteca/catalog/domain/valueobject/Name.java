package me.helioalbano.biblioteca.catalog.domain.valueobject;

import me.helioalbano.biblioteca.catalog.domain.exceptions.InvalidAuthorNameException;

public class Name {

    private String[] fullName;
    private String firstName;
    private String lastName;

    public Name(final String fullName) {
        this.fullName = split(fullName);
    }

    private String[] split(String name) {
        if (name == null) throw new InvalidAuthorNameException("name.null");

        var splitName = name.split(" ");
        if (splitName.length < 2) {
            throw new InvalidAuthorNameException("name.length.less.than.two");
        }
        return splitName;
    }

    public String getFirstName() {
        return fullName[0];
    }

    public String getLastName() {
        return fullName[fullName.length - 1];
    }

    @Override
    public String toString() {
        return String.join(" ", fullName);
    }
}
