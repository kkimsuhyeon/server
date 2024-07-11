package com.project.mybnb.consumer.exception;

public record ConsumerErrorResponse(
        int status,
        String errorCode,
        String message
) {
}
