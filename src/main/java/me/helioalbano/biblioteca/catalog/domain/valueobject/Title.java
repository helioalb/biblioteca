package me.helioalbano.biblioteca.catalog.domain.valueobject;

import me.helioalbano.biblioteca.catalog.domain.exceptions.TitleFormatException;

public class Title {
    private String value;

    public Title(String title) {
        this.value = title;
        this.validate();
    }

    private void validate() {
        if (this.value == null) {
            throw new TitleFormatException("title.null");
        }

        if (this.value.length() > 50) {
            throw new TitleFormatException("title.greather.than.50");
        }

        if (this.value.length() == 0) {
            throw new TitleFormatException("title.length.0");
        }
    }

    @Override
    public String toString() {
        return this.value;
    }
}
