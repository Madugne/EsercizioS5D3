package chunyin.EsercizioS5D3.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException{
    public NotFoundException(UUID id){
        super("Elemento con id: " + id + " non trovato");
    }
}
