package com.project.mybnb.businessMember.dto.request;

import com.project.mybnb.businessMember.dto.BusinessMemberDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record RequestSigninBusinessMemberDto(
        @NotNull
        @Email
        String email,

        @NotNull
        String password) implements RequestBusinessMemberDto {

    @Override
    public BusinessMemberDto toDto() {
        return new BusinessMemberDto.Builder(email, password).build();
    }
}
