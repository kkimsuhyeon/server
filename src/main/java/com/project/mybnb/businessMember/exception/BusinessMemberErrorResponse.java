package com.project.mybnb.businessMember.exception;

import lombok.Getter;

public record BusinessMemberErrorResponse(
        int status,
        String errorCode,
        String message
) {
}
