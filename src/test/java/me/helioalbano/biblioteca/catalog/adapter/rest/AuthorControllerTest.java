package me.helioalbano.biblioteca.catalog.adapter.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.helioalbano.biblioteca.catalog.adapter.rest.dto.CreateAuthorRequest;
import me.helioalbano.biblioteca.catalog.usecase.author.CreateAuthor;
import me.helioalbano.biblioteca.catalog.usecase.author.ListAuthors;
import me.helioalbano.biblioteca.catalog.usecase.author.ShowAuthor;
import me.helioalbano.biblioteca.catalog.usecase.author.dto.AuthorOutput;
import me.helioalbano.biblioteca.catalog.usecase.exceptions.AuthorNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(AuthorController.class)
class AuthorControllerTest {

  private static final String RESOURCE_PATH = "/catalog/authors/";
  private static final Integer VALID_AUTHOR_ID = 10;
  private static final Integer INVALID_AUTHOR_ID = 999;

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
  void givenValidRequestToCreateNewAuthor_whenCreatingAuthor_thenReturnIsCreated() throws Exception {
    var request = buildValidRequestToCreateNewAuthor();

    mockMvc.perform(request)
      .andExpect(status().isCreated())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(header().string("Location", RESOURCE_PATH + VALID_AUTHOR_ID));
  }

  private MockHttpServletRequestBuilder buildValidRequestToCreateNewAuthor() throws JsonProcessingException {
    var content = mapper.writeValueAsString(buildValidAuthor());

    when(createAuthor.execute(any())).thenReturn(VALID_AUTHOR_ID.longValue());

    return MockMvcRequestBuilders.post(RESOURCE_PATH)
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON)
      .content(content);
  }

  private CreateAuthorRequest buildValidAuthor() {
    var request = new CreateAuthorRequest();
    request.setName("Robert C. Martin");

    return request;
  }

  @Test
  void givenInvalidRequestToGetOneAuthor_whenQueryOneAuthor_thenReturnNotFound() throws Exception {
    var request = buildInvalidRequestToGetOneAuthor();

    mockMvc.perform(request)
      .andExpect(status().isNotFound())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

  private MockHttpServletRequestBuilder buildInvalidRequestToGetOneAuthor() {
    when(showAuthor.execute(any(Long.class))).thenThrow(AuthorNotFoundException.class);

    return MockMvcRequestBuilders.get(RESOURCE_PATH + INVALID_AUTHOR_ID)
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON);
  }

  @Test
  void givenValidRequestToGetOneAuthor_whenQueryOneAuthor_thenReturnAnAuthor() throws Exception {
    var request = buildValidRequestToGetOneAuthor();

    mockMvc.perform(request)
      .andExpect(status().isOk())
      .andExpect(content().contentType(MediaType.APPLICATION_JSON))
      .andExpect(jsonPath("$.id").value(VALID_AUTHOR_ID));
  }

  private MockHttpServletRequestBuilder buildValidRequestToGetOneAuthor() {
    when(showAuthor.execute(any(Long.class))).thenReturn(buildOneAuthor());

    return MockMvcRequestBuilders.get(RESOURCE_PATH + VALID_AUTHOR_ID)
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON);
  }

  private AuthorOutput buildOneAuthor() {
    return new AuthorOutput(VALID_AUTHOR_ID.longValue(), "Robert C. Martin");
  }
}
