package com.app.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class InternalErrorException extends RuntimeException
{
    public InternalErrorException(String exception) {
        super(exception);
    }
}
