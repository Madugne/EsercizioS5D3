package chunyin.EsercizioS5D3.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorPayload handleBadRequest(BadRequestException ex){
        return new ErrorPayload(ex.getMessage(), LocalDate.now());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorPayload handleNotFound(NotFoundException ex){
        return new ErrorPayload(ex.getMessage(), LocalDate.now());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    public ErrorPayload handleGenericErrors(Exception ex){
        ex.printStackTrace();
        return new ErrorPayload("Errore interno server", LocalDate.now());
    }
}
