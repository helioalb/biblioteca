package me.helioalbano.biblioteca.catalog.config;

import me.helioalbano.biblioteca.catalog.domain.repository.BookRepository;
import me.helioalbano.biblioteca.catalog.infra.repository.BookRepositoryInMemory;
import me.helioalbano.biblioteca.catalog.usecase.addnewbook.AddNewBook;
import me.helioalbano.biblioteca.catalog.usecase.addnewbook.impl.AddNewBookImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CatalogConfig {

    @Bean
    public BookRepository bookRepository() {
        return new BookRepositoryInMemory();
    }

    @Bean
    public AddNewBook addNewBook() {
        return new AddNewBookImpl(bookRepository());
    }
}
