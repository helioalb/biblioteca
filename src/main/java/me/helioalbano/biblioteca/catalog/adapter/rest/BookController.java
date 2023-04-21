package me.helioalbano.biblioteca.catalog.adapter.rest;

import me.helioalbano.biblioteca.catalog.adapter.rest.dto.BookResponse;
import me.helioalbano.biblioteca.catalog.adapter.rest.dto.CreateBookRequest;
import me.helioalbano.biblioteca.catalog.usecase.CreateBook;
import me.helioalbano.biblioteca.catalog.usecase.ListBooks;
import me.helioalbano.biblioteca.catalog.usecase.ShowBook;
import me.helioalbano.biblioteca.catalog.usecase.dto.BookOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("catalog")
class BookController {
  private final CreateBook createBook;
  private final ListBooks listBooks;
  private final ShowBook showBook;

  public BookController(CreateBook createBook, ListBooks listBooks, ShowBook showBook) {
    this.createBook = createBook;
    this.listBooks = listBooks;
    this.showBook = showBook;
  }

  @PostMapping(path = "books")
  public ResponseEntity<Void> create(@Valid @RequestBody CreateBookRequest book) {
    var bookId = createBook.execute(book.toUseCaseInput());
    return ResponseEntity.created(resourcePathTo(bookId)).build();
  }

  private URI resourcePathTo(Long bookId) {
    return URI.create("/catalog/books/" + bookId);
  }

  @GetMapping(path = "books")
  public List<BookResponse> index(
    @RequestParam(name = "page-number", required = false, defaultValue = "0") Integer page,
    @RequestParam(name = "number-of-results-per-page", required = false, defaultValue = "10") Integer numberOfResultsPerPage) {
    var books = listBooks.execute(page, numberOfResultsPerPage);
    return books.stream().map(this::buildBookResponse).toList();
  }

  private BookResponse buildBookResponse(BookOutput book) {
    return new BookResponse(book.id(), book.title());
  }

  @GetMapping(path = "books/{bookId}", consumes = "application/json")
  public ResponseEntity<BookResponse> show(@PathVariable Long bookId) {
    var book = showBook.execute(bookId);
    return ResponseEntity.ok(buildBookResponse(book));
  }
}
