package me.helioalbano.biblioteca.catalog.adapter.database;

import me.helioalbano.biblioteca.catalog.adapter.database.postgres.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepositoryJPA extends JpaRepository<AuthorEntity, Long> {
}
