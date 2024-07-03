package com.project.mybnb.businessMember.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class BusinessMemberExceptionHandler {

    @ExceptionHandler(BusinessMemberException.class)
    public ResponseEntity<BusinessMemberErrorResponse> handleBusinessMemberException(BusinessMemberException ex, WebRequest request) {
        BusinessMemberErrorResponse response = new BusinessMemberErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getErrorCode(),
                ex.getMessage()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
