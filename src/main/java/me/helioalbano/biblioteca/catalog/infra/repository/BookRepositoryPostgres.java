package me.helioalbano.biblioteca.catalog.infra.repository;

import me.helioalbano.biblioteca.catalog.domain.entity.Book;
import me.helioalbano.biblioteca.catalog.domain.repository.BookRepository;
import me.helioalbano.biblioteca.catalog.domain.valueobject.Title;
import me.helioalbano.biblioteca.catalog.infra.repository.postgres.BookRepositoryJPA;
import me.helioalbano.biblioteca.catalog.infra.repository.postgres.entity.BookEntity;
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
        return new Book(bookEntity.getId(), new Title(bookEntity.getTitle()));
    }
}
