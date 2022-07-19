package me.helioalbano.biblioteca.catalog.usecase.addnewbook;

import me.helioalbano.biblioteca.catalog.usecase.addnewbook.dto.AddNewBookInput;

public interface AddNewBook {
    void execute(AddNewBookInput addNewBookInput);
}
