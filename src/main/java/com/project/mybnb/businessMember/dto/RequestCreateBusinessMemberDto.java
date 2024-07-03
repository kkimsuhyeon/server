package com.project.mybnb.businessMember.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.ToString;

public record RequestCreateBusinessMemberDto(
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
