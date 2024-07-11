package com.project.mybnb.consumer.dto.request;

import com.project.mybnb.consumer.dto.ConsumerDto;
import jakarta.validation.constraints.NotNull;

public record RequestUpdateConsumerDto(
        @NotNull
        String password,

        @NotNull
        String name
) implements RequestConsumerDto {

    public ConsumerDto toDto() {
        return new ConsumerDto.Builder(null, password)
                .name(name)
                .build();
    }

}
