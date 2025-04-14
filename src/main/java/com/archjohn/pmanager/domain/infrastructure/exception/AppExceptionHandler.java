package com.archjohn.pmanager.domain.infrastructure.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = RequestException.class)
    public ResponseEntity<Object> handleRequestException(RequestException exception, WebRequest request) {
        return handleException(exception, exception.getErrorCode(), exception.getMessage(), BAD_REQUEST, request);
    }

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
    private ResponseEntity<Object> handleException(
            Exception exception,
            String errorCode,
            String message,
            HttpStatus status,
            WebRequest request
            ) {
        ServletWebRequest servletWebRequest = (ServletWebRequest) request;

        return handleExceptionInternal(
                exception,
                RestError
                        .builder()
                        .errorCode(errorCode)
                        .errorMessage(message)
                        .statusCode(status.value())
                        .path(servletWebRequest.getRequest().getRequestURI())
                        .build(),
                new HttpHeaders(),
                status,
                request
        );
    }
}
