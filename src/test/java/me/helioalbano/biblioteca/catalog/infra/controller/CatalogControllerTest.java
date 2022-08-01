package me.helioalbano.biblioteca.catalog.infra.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.helioalbano.biblioteca.catalog.infra.controller.dto.CreateBookRequest;
import me.helioalbano.biblioteca.catalog.usecase.book.create.Create;
import me.helioalbano.biblioteca.catalog.usecase.book.create.dto.CreateBookInput;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(CatalogController.class)
class CatalogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private Create create;

    @Test
    void createBook_success() throws Exception {
        var request = new CreateBookRequest();
        request.setTitle("O Programador Pragmático: de aprendiz a mestre");

        when(create.execute(request.toUseCaseInput())).thenReturn(10L);

        var mockRequest = MockMvcRequestBuilders.post("/catalog/books/")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(request));

        mockMvc.perform(mockRequest)
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", "/catalog/books/10"));
    }

    @Test
    void createBook_emptyTitle_error() throws Exception {
        var request = new CreateBookRequest();
        request.setTitle(null);
        var mockRequest = MockMvcRequestBuilders.post("/catalog/books/")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(request));

        mockMvc.perform(mockRequest)
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.title", Is.is("The book title must be informed")));
    }

    @Test
    void createBook_titleLengthLessThan3_error() throws Exception {
        var request = new CreateBookRequest();
        request.setTitle("Oi");
        var mockRequest = MockMvcRequestBuilders.post("/catalog/books/")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(mapper.writeValueAsString(request));

        mockMvc.perform(mockRequest)
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.title", Is.is("size must be between 3 and 50")));
    }
}
