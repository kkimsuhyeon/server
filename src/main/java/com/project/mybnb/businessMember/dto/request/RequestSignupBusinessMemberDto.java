package com.project.mybnb.businessMember.dto.request;

import com.project.mybnb.businessMember.dto.BusinessMemberDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record RequestSignupBusinessMemberDto(
        @NotNull
        @Email
        String email,

        @NotNull
        String password,

        @NotNull
        String name,

        @NotNull
        String companyName) implements RequestBusinessMemberDto {

    @Override
    public BusinessMemberDto toDto() {
        return new BusinessMemberDto.Builder(email, password)
                .name(name)
                .build();
    }
}
