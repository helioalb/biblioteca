package me.helioalbano.biblioteca.catalog.infra.repository.postgres;

import me.helioalbano.biblioteca.catalog.infra.repository.postgres.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepositoryJPA extends JpaRepository<AuthorEntity, Long> {
}
