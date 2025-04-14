package com.archjohn.pmanager.domain.infrastructure.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception exception, WebRequest request) {
        return handleExceptionInternal(
                exception,
                "Error:",
                new HttpHeaders(),
                BAD_REQUEST,
                request
        );
    }
}
