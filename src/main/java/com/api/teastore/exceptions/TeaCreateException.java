package com.api.teastore.exceptions;

import org.springframework.http.HttpStatus;

public class TeaCreateException extends ApiException{
    public TeaCreateException(String message){
        super(HttpStatus.BAD_REQUEST, message);
    }
}
