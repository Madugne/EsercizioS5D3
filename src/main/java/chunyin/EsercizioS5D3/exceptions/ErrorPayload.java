package chunyin.EsercizioS5D3.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class ErrorPayload {
    private String message;
    private LocalDate timestamp;
}
