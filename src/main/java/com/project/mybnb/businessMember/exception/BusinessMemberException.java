package com.project.mybnb.businessMember.exception;

import lombok.Getter;

@Getter
public class BusinessMemberException extends RuntimeException {

    private final String errorCode;

    public BusinessMemberException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
