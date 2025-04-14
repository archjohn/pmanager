package com.archjohn.pmanager.domain.infrastructure.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception exception, WebRequest request) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;

        return handleExceptionInternal(
                exception,
                RestError
                        .builder()
                        .errorCode("400")
                        .errorMessage(exception.getMessage())
                        .statusCode(BAD_REQUEST.value())
                        .path(servletWebRequest.getRequest().getRequestURI())
                        .build(),
                new HttpHeaders(),
                BAD_REQUEST,
                request
        );
    }
}
