package com.api.teastore.exceptions;

import org.springframework.http.HttpStatus;

public class TeaNotFoundException extends ApiException {
    public TeaNotFoundException(String message){
        super(HttpStatus.NOT_FOUND, message);
    }
}
