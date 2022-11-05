package me.helioalbano.biblioteca.catalog.infra.repository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import me.helioalbano.biblioteca.catalog.domain.entity.Author;
import me.helioalbano.biblioteca.catalog.domain.repository.AuthorRepository;
import me.helioalbano.biblioteca.catalog.infra.repository.postgres.AuthorRepositoryJPA;
import me.helioalbano.biblioteca.catalog.infra.repository.postgres.entity.AuthorEntity;


public class AuthorRepositoryPostgres implements AuthorRepository {

    @Autowired
    private AuthorRepositoryJPA authorRepositoryJPA;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Author create(Author author) {
        var authorEntity = modelMapper.map(author, AuthorEntity.class);
        authorEntity = authorRepositoryJPA.save(authorEntity);
        return Author.load(authorEntity.getId(), authorEntity.getName());
    }
}
