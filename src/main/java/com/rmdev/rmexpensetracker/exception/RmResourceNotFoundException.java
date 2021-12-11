package com.rmdev.rmexpensetracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RmResourceNotFoundException extends RuntimeException {

    public RmResourceNotFoundException(String message) {
        super(message);
    }
}
