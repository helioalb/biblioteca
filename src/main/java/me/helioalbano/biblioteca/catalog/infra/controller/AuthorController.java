package me.helioalbano.biblioteca.catalog.infra.controller;

import me.helioalbano.biblioteca.catalog.infra.controller.dto.AuthorResponse;
import me.helioalbano.biblioteca.catalog.infra.controller.dto.CreateAuthorRequest;
import me.helioalbano.biblioteca.catalog.usecase.CreateAuthor;
import me.helioalbano.biblioteca.catalog.usecase.ListAuthors;
import me.helioalbano.biblioteca.catalog.usecase.ShowAuthor;
import me.helioalbano.biblioteca.catalog.usecase.dto.AuthorOutput;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "catalog", consumes = "application/json")
public class AuthorController {
  private final CreateAuthor createAuthor;
  private final ShowAuthor showAuthor;
  private final ListAuthors listAuthors;

  public AuthorController(CreateAuthor createAuthor, ShowAuthor showAuthor,
                          ListAuthors listAuthors) {
    this.createAuthor = createAuthor;
    this.showAuthor = showAuthor;
    this.listAuthors = listAuthors;
  }

  @PostMapping(path = "authors")
  public ResponseEntity<Void> create(@Valid @RequestBody CreateAuthorRequest author) {
    var authorId = createAuthor.execute(author.toUseCaseInput());
    return ResponseEntity.created(buildResourcePath(authorId))
      .contentType(MediaType.APPLICATION_JSON)
      .build();
  }

  private URI buildResourcePath(Long authorId) {
    return URI.create("/catalog/authors/" + authorId);
  }

  @GetMapping(path = "authors")
  public List<AuthorResponse> index(
    @RequestParam(name = "page-number", required = false, defaultValue = "0") Integer page,
    @RequestParam(name = "number-of-results-per-page", required = false, defaultValue = "10") Integer numberOfResultsPerPage) {
    List<AuthorOutput> authors = listAuthors.execute(page, numberOfResultsPerPage);
    return authors.stream().map(this::buildAuthorResponse).toList();
  }

  @GetMapping(path = "authors/{authorId}", consumes = "application/json")
  public ResponseEntity<AuthorResponse> show(@PathVariable Long authorId) {
    var author = showAuthor.execute(authorId);
    return ResponseEntity.ok(buildAuthorResponse(author));
  }

  private AuthorResponse buildAuthorResponse(AuthorOutput author) {
    return AuthorResponse.create(author.id(), author.name());
  }
}
