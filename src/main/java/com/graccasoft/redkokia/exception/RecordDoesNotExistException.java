package com.graccasoft.redkokia.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecordDoesNotExistException extends RuntimeException {
    public RecordDoesNotExistException(String message) {
        super(message);
    }
}
