package me.helioalbano.biblioteca.catalog.domain.valueobject;

import org.springframework.util.Assert;

public class Title {
    private String value;

    public Title(String title) {
        this.setTitle(title);
    }

    private void setTitle(String title) {
        Assert.notNull(title, "title.null");
        Assert.isTrue(title.length() <= 50, "title.greather.than.50");
        Assert.isTrue(title.length() > 0, "title.length.zero");
        this.value = title;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
