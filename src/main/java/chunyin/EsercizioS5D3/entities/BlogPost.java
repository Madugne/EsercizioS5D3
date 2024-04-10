package chunyin.EsercizioS5D3.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "blogPosts")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class BlogPost {
    @Id
    @GeneratedValue
    private UUID id;
    private String category;
    private String title;
    private String coverURL;
    private String content;
    private double readingTime;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
}
