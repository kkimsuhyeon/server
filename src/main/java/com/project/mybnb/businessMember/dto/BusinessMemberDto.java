package com.project.mybnb.businessMember.dto;

import com.project.mybnb.businessMember.domain.BusinessMember;
import com.project.mybnb.businessMember.dto.request.RequestBusinessMemberDto;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BusinessMemberDto {

    private Long id;
    private String email;
    private String password;
    private String name;
    private String companyName;

    public BusinessMemberDto(Builder builder) {
        this.id = builder.id;
        this.email = builder.email;
        this.password = builder.password;
        this.name = builder.name;
        this.companyName = builder.companyName;
    }

    public static BusinessMemberDto fromEntity(BusinessMember entity) {
        return new Builder(entity.getEmail(), entity.getPassword())
                .id(entity.getId())
                .name(entity.getName())
                .companyName(entity.getCompanyName())
                .build();
    }

    public static BusinessMemberDto fromRequest(RequestBusinessMemberDto request) {
        return request.toDto();
    }

    public BusinessMember toEntity() {
        return BusinessMember.builder()
                .id(id)
                .email(email)
                .password(password)
                .name(name)
                .companyName(companyName)
                .build();
    }

    public static class Builder {
        private Long id;
        private String email;
        private String password;
        private String name;
        private String companyName;

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

        public Builder companyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        public BusinessMemberDto build() {
            return new BusinessMemberDto(this);
        }
    }
}
