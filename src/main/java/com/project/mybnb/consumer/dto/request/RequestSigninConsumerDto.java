package com.project.mybnb.consumer.dto.request;

import com.project.mybnb.consumer.dto.ConsumerDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record RequestSigninConsumerDto(
        @NotNull
        @Email
        String email,

        @NotNull
        String password) implements RequestConsumerDto {

    @Override
    public ConsumerDto toDto() {
        return new ConsumerDto.Builder(email, password).build();
    }
}
