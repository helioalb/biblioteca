package me.helioalbano.biblioteca.catalog.domain.entity.author;

import org.springframework.util.Assert;

public class Name {

    private String[] fullName;

    public Name(final String fullName) {
        this.fullName = split(fullName);
    }

    private String[] split(String name) {
        Assert.notNull(name, "name.null");

        var splitName = name.split(" ");
        Assert.isTrue(splitName.length > 1, "name.length.less.than.two.parts");
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
