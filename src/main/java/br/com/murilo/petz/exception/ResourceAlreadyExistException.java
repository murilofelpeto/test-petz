package br.com.murilo.petz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceAlreadyExistException extends RuntimeException{

    public ResourceAlreadyExistException(final String message) {
        super(message);
    }
}
