package com.project.mybnb.consumer.exception;

import lombok.Getter;

@Getter
public class ConsumerException extends RuntimeException {

    private final String errorCode;

    public ConsumerException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
