package com.bard.newssitedata.utils;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value
            = {IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity<String> handleAccessDeniedException(
            Exception ex, WebRequest request) {
        String bodyOfResponse = "Incorrect value was provided";
        return new ResponseEntity<>(bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
