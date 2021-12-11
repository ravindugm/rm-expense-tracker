package com.rmdev.rmexpensetracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RmBadRequestException extends RuntimeException {

    public RmBadRequestException(String message) {
        super(message);
    }
}
