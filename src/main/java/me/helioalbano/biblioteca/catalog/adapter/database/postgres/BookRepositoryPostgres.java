package me.helioalbano.biblioteca.catalog.adapter.database.postgres;

import me.helioalbano.biblioteca.catalog.adapter.database.BookRepositoryJPA;
import me.helioalbano.biblioteca.catalog.domain.book.Book;
import me.helioalbano.biblioteca.catalog.repository.BookRepository;
import me.helioalbano.biblioteca.catalog.adapter.database.postgres.entity.BookEntity;
import me.helioalbano.biblioteca.catalog.usecase.exceptions.BookNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public class BookRepositoryPostgres implements BookRepository {
  @Autowired
  private BookRepositoryJPA bookRepositoryJPA;

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public Book create(Book book) {
    var bookEntity = modelMapper.map(book, BookEntity.class);
    bookEntity = bookRepositoryJPA.save(bookEntity);
    return Book.load(bookEntity.getId(), bookEntity.getTitle());
  }

  @Override
  public List<Book> findAll(Integer pageNumber, Integer numberOfResultsPerPage) {
    var pageRequest = PageRequest.of(pageNumber, numberOfResultsPerPage);
    return bookRepositoryJPA.findAll(pageRequest)
      .stream().map(this::buildBook).toList();
  }

  @Override
  public Book findById(Long id) {
    var bookEntity = bookRepositoryJPA.findById(id);

    if (bookEntity.isEmpty())
      throw new BookNotFoundException();

    return buildBook(bookEntity.get());
  }

  private Book buildBook(BookEntity book) {
    return Book.load(book.getId(), book.getTitle());
  }
}
