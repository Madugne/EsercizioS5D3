package chunyin.EsercizioS5D3.payloads;

import chunyin.EsercizioS5D3.entities.Author;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record NewBlogPostDTO(
        @NotEmpty(message = "La categoria è obbligatoria")
        @Size(min = 3, max = 30, message = "La categoria essere compresa tra i 3 e i 30 caratteri")
        String category,
        @NotEmpty(message = "Il titolo è obbligatorio")
        @Size(min = 3, max = 30, message = "Il titolo tra i 3 e i 30 caratteri")
        String title,
        @NotEmpty(message = "Il contenuto è obbligatorio")
        @Size(min = 3, max = 100, message = "Il contenuto ha un minimo di 3 e un massimo di 100 caratteri")
        String content,
        @NotEmpty(message = "Il tempo di lettura è obbligatorio")
        double readingTime,
        @NotEmpty
        Author author
) {
}
