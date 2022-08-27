package me.helioalbano.biblioteca.catalog.config;

import me.helioalbano.biblioteca.catalog.domain.repository.AuthorRepository;
import me.helioalbano.biblioteca.catalog.domain.repository.BookRepository;
import me.helioalbano.biblioteca.catalog.infra.repository.AuthorRepositoryPostgres;
import me.helioalbano.biblioteca.catalog.infra.repository.BookRepositoryPostgres;
import me.helioalbano.biblioteca.catalog.usecase.CreateAuthor;
import me.helioalbano.biblioteca.catalog.usecase.CreateBook;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CatalogConfig {

    @Bean
    public BookRepository bookRepository() {
        return new BookRepositoryPostgres();
    }

    @Bean
    public CreateBook createBook() {
        return new CreateBook(bookRepository());
    }

    @Bean
    public AuthorRepository authorRepository() {
        return new AuthorRepositoryPostgres();
    }

    @Bean
    public CreateAuthor createAuthor() {
        return new CreateAuthor(authorRepository());
    }
}
