package me.helioalbano.biblioteca.catalog.adapter.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.helioalbano.biblioteca.catalog.adapter.rest.dto.CreateAuthorRequest;
import me.helioalbano.biblioteca.catalog.usecase.CreateAuthor;
import me.helioalbano.biblioteca.catalog.usecase.ListAuthors;
import me.helioalbano.biblioteca.catalog.usecase.ShowAuthor;
import me.helioalbano.biblioteca.catalog.usecase.dto.AuthorOutput;
import me.helioalbano.biblioteca.catalog.usecase.exceptions.AuthorNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(AuthorController.class)
class AuthorControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper mapper;

  @MockBean
  private CreateAuthor createAuthor;

  @MockBean
  private ShowAuthor showAuthor;

  @MockBean
  private ListAuthors listAuthors;

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
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(header().string("Location", "/catalog/authors/10"));
  }

  @Test
  void givenInValidAuthorId_whenQueryOneAuthor_thenReturnNotFound() throws Exception {
    when(showAuthor.execute(any(Long.class))).thenThrow(AuthorNotFoundException.class);

    var mockRequest = MockMvcRequestBuilders.get("/catalog/authors/100")
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON);

    mockMvc.perform(mockRequest)
      .andExpect(status().isNotFound())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

  @Test
  void givenValidAuthorId_whenQueryOneAuthor_thenReturnAnAuthor() throws Exception {
    when(showAuthor.execute(any(Long.class))).thenReturn(buildOneAuthor());

    var mockRequest = MockMvcRequestBuilders.get("/catalog/authors/1")
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON);

    mockMvc.perform(mockRequest)
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.id").value(1))
      .andExpect(jsonPath("$.name").value("Robert C. Martin"));
  }

  private AuthorOutput buildOneAuthor() {
    return new AuthorOutput(1L, "Robert C. Martin");
  }
}
