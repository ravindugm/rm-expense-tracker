package com.rmdev.rmexpensetracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class RmAuthException extends RuntimeException {

    public RmAuthException(String message) {
        super(message);
    }

}
