package me.helioalbano.biblioteca.catalog.config;

import me.helioalbano.biblioteca.catalog.domain.repository.BookRepository;
import me.helioalbano.biblioteca.catalog.infra.repository.BookRepositoryPostgres;
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
    public CreateBook createBookBook() {
        return new CreateBook(bookRepository());
    }
}
