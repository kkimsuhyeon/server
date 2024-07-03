package com.project.mybnb.businessMember.dto;

import jakarta.validation.constraints.NotNull;

public record RequestUpdateBusinessMemberDto(
        @NotNull
        String password,

        @NotNull
        String name,

        @NotNull
        String companyName) implements RequestBusinessMemberDto {

    public BusinessMemberDto toDto() {
        return new BusinessMemberDto.Builder(null, password)
                .name(name)
                .build();
    }

}
