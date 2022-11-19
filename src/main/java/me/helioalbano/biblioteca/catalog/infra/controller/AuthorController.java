package me.helioalbano.biblioteca.catalog.infra.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.helioalbano.biblioteca.catalog.infra.controller.dto.CreateAuthorRequest;
import me.helioalbano.biblioteca.catalog.usecase.CreateAuthor;

@RestController
@RequestMapping("catalog")
public class AuthorController {
  private final CreateAuthor createAuthor;

  public AuthorController(CreateAuthor createAuthor) {
    this.createAuthor = createAuthor;
  }

  @PostMapping(path = "authors")
  public ResponseEntity<Void> create(@Valid @RequestBody CreateAuthorRequest author) {
    var authorId = createAuthor.execute(author.toUseCaseInput());
    return ResponseEntity.created(resourcePathTo(authorId)).build();
  }

  private URI resourcePathTo(Long authorId) {
    return URI.create("/catalog/authors/" + authorId);
  }
}
