package me.helioalbano.biblioteca.catalog.adapter.database.postgres;

import me.helioalbano.biblioteca.catalog.adapter.database.AuthorRepositoryJPA;
import me.helioalbano.biblioteca.catalog.adapter.database.postgres.entity.AuthorEntity;
import me.helioalbano.biblioteca.catalog.domain.author.Author;
import me.helioalbano.biblioteca.catalog.repository.AuthorRepository;
import me.helioalbano.biblioteca.catalog.usecase.exceptions.AuthorNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.List;


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

  @Override
  public Author findById(Long id) {
    var authorEntity = authorRepositoryJPA.findById(id);

    if (authorEntity.isEmpty())
      throw new AuthorNotFoundException();

    return Author.load(authorEntity.get().getId(), authorEntity.get().getName());
  }

  @Override
  public List<Author> findAll(Integer pageNumber, Integer numberOfResultsPerPage) {
    var pageRequest = PageRequest.of(pageNumber, numberOfResultsPerPage);
    return authorRepositoryJPA.findAll(pageRequest)
      .stream().map(this::buildAuthor).toList();
  }

  private Author buildAuthor(AuthorEntity author) {
    return Author.load(author.getId(), author.getName());
  }
}
