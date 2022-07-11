package me.helioalbano.biblioteca.catalog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import me.helioalbano.biblioteca.catalog.domain.repository.BookRepository;
import me.helioalbano.biblioteca.catalog.infra.repository.BookRepositoryPostgres;
import me.helioalbano.biblioteca.catalog.usecase.addnewbook.AddNewBook;
import me.helioalbano.biblioteca.catalog.usecase.addnewbook.impl.AddNewBookImpl;

@Configuration
public class CatalogConfig {

    @Bean
    public BookRepository bookRepository() {
        return new BookRepositoryPostgres();
    }

    @Bean
    public AddNewBook addNewBook() {
        return new AddNewBookImpl(bookRepository());
    }
}
