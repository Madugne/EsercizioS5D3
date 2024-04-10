package chunyin.EsercizioS5D3.repositories;

import chunyin.EsercizioS5D3.entities.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BlogPostDAO extends JpaRepository <BlogPost, UUID>{
}
