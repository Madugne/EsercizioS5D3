package chunyin.EsercizioS5D3.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="authors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Author {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String surname;
    private String email;
    private LocalDate birthday;
    private String avatarURL;
    @OneToMany(mappedBy = "author")
    @JsonIgnore
    private List<BlogPost> blogPosts;

    public Author(String name, String surname, String email, LocalDate birthday, String avatarURL) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.birthday = birthday;
        this.avatarURL = avatarURL;
    }
}
