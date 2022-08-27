package me.helioalbano.biblioteca.catalog.domain.valueobject;

import me.helioalbano.biblioteca.catalog.domain.exceptions.TitleFormatException;

public class Title {
    private String value;

    public Title(String title) {
        this.setTitle(title);
    }

    private void setTitle(String title) {
        if (title == null) {
            throw new TitleFormatException("title.null");
        }

        if (title.length() > 50) {
            throw new TitleFormatException("title.greather.than.50");
        }

        if (title.length() == 0) {
            throw new TitleFormatException("title.length.0");
        }
        this.value = title;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
