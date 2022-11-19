package me.helioalbano.biblioteca.catalog.infra.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.helioalbano.biblioteca.catalog.infra.controller.dto.CreateAuthorRequest;
import me.helioalbano.biblioteca.catalog.usecase.CreateAuthor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AuthorController.class)
class AuthorControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper mapper;

  @MockBean
  private CreateAuthor createAuthor;

  @Test
  void givenValidAuthor_whenCreatingAuthor_thenReturnIsCreated() throws Exception {
    var request = new CreateAuthorRequest();
    request.setName("Robert C. Martin");

    when(createAuthor.execute(any())).thenReturn(10L);

    var mockRequest = MockMvcRequestBuilders.post("/catalog/authors")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsString(request));

    mockMvc.perform(mockRequest)
        .andExpect(status().isCreated())
        .andExpect(header().string("Location", "/catalog/authors/10"));
  }
}
