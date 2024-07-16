package com.api.teastore.exceptions;


import com.api.teastore.dto.ErrorDto;
import com.api.teastore.dto.ErrorResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = TeaControllerExceptionHandler.class)
public class ApiExceptionHandler {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponseDto> handleCreationException(ApiException exception){
        var error = ErrorDto.builder()
                .message(exception.getMessage())
                .build();

        var result = new ErrorResponseDto().setError(error);

        return ResponseEntity.status(exception.getHttpStatus())
                .body(result);
    }
}
