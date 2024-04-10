package chunyin.EsercizioS5D3.repositories;

import chunyin.EsercizioS5D3.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthorDAO extends JpaRepository<Author, UUID> {
    boolean existsByEmail(String email);
    Optional<Author> findByEmail(String email);
}
