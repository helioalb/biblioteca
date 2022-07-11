package me.helioalbano.biblioteca.catalog.infra.repository.postgres;

import me.helioalbano.biblioteca.catalog.infra.repository.postgres.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepositoryJPA extends JpaRepository<BookEntity, Long> {
}
