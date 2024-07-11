package com.project.mybnb.consumer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ConsumerExceptionHandler {

    @ExceptionHandler(ConsumerException.class)
    public ResponseEntity<ConsumerErrorResponse> handleConsumerException(ConsumerException ex, WebRequest request) {
        ConsumerErrorResponse response = new ConsumerErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getErrorCode(),
                ex.getMessage()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
