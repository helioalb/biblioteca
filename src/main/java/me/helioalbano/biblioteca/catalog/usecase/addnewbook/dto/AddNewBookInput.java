package me.helioalbano.biblioteca.catalog.usecase.addnewbook.dto;

public class AddNewBookInput {
    private String title;

    public AddNewBookInput(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
