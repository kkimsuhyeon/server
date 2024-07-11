package com.project.mybnb.consumer.dto;

import com.project.mybnb.consumer.domain.Consumer;
import com.project.mybnb.consumer.dto.request.RequestConsumerDto;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ConsumerDto {

    private Long id;
    private String email;
    private String password;
    private String name;

    public ConsumerDto(Builder builder) {
        this.id = builder.id;
        this.email = builder.email;
        this.password = builder.password;
    }

    public static ConsumerDto fromEntity(Consumer entity) {
        return new Builder(entity.getEmail(), entity.getPassword())
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }

    public static ConsumerDto fromRequest(RequestConsumerDto request) {
        return request.toDto();
    }

    public Consumer toEntity() {
        return Consumer.builder()
                .email(email)
                .password(password)
                .build();
    }

    public static class Builder {
        private Long id;
        private String email;
        private String password;
        private String name;

        public Builder(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public ConsumerDto build() {
            return new ConsumerDto(this);
        }
    }
}
