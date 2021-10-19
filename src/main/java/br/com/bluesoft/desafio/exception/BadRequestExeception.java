package br.com.bluesoft.desafio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * sempre vai retornar status 404 bad request
 */

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestExeception extends RuntimeException {

    public BadRequestExeception(String message) {
        super(message);
    }
}
