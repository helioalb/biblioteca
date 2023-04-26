package me.helioalbano.biblioteca.catalog.adapter;

import me.helioalbano.biblioteca.catalog.repository.AuthorRepository;
import me.helioalbano.biblioteca.catalog.repository.BookRepository;
import me.helioalbano.biblioteca.catalog.adapter.database.postgres.AuthorRepositoryPostgres;
import me.helioalbano.biblioteca.catalog.adapter.database.postgres.BookRepositoryPostgres;
import me.helioalbano.biblioteca.catalog.usecase.author.CreateAuthor;
import me.helioalbano.biblioteca.catalog.usecase.author.ListAuthors;
import me.helioalbano.biblioteca.catalog.usecase.author.ShowAuthor;
import me.helioalbano.biblioteca.catalog.usecase.book.CreateBook;
import me.helioalbano.biblioteca.catalog.usecase.book.ListBooks;
import me.helioalbano.biblioteca.catalog.usecase.book.ShowBook;
import me.helioalbano.biblioteca.catalog.usecase.book.UpdateBookTitle;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CatalogConfig {

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Bean
  public BookRepository bookRepository() {
    return new BookRepositoryPostgres();
  }

  @Bean
  public CreateBook createBook() {
    return new CreateBook(bookRepository(), authorRepository());
  }

  @Bean
  public ListBooks listBooks() {
    return new ListBooks(bookRepository());
  }

  @Bean
  public ShowBook showBook() {
    return new ShowBook(bookRepository());
  }

  @Bean
  public UpdateBookTitle updateBookTitle() {
    return new UpdateBookTitle(bookRepository());
  }

  @Bean
  public AuthorRepository authorRepository() {
    return new AuthorRepositoryPostgres();
  }

  @Bean
  public CreateAuthor createAuthor() {
    return new CreateAuthor(authorRepository());
  }

  @Bean
  public ShowAuthor showAuthor() {
    return new ShowAuthor(authorRepository());
  }

  @Bean
  public ListAuthors listAuthors() {
    return new ListAuthors(authorRepository());
  }
}
