package me.helioalbano.biblioteca.catalog.unit;

import me.helioalbano.biblioteca.catalog.infra.controller.dto.CreateBookRequest;
import me.helioalbano.biblioteca.catalog.usecase.addnewbook.dto.AddNewBookInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

public class ModelMapperTest {

    @Test
    void conversionBetweenCreateBookRequestAndAddNewBookInput() {
        var modelMapper = new ModelMapper();
        var request = new CreateBookRequest();
        request.setTitle("Implementando Domain-Driven Design");

        var input = modelMapper.map(request, AddNewBookInput.class);
        Assertions.assertEquals(request.getTitle(), input.getTitle());
    }
}
