package me.helioalbano.biblioteca.catalog.adapter.database.postgres;

import me.helioalbano.biblioteca.catalog.adapter.database.BookRepositoryJPA;
import me.helioalbano.biblioteca.catalog.domain.book.Book;
import me.helioalbano.biblioteca.catalog.repository.BookRepository;
import me.helioalbano.biblioteca.catalog.adapter.database.postgres.entity.BookEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

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
}