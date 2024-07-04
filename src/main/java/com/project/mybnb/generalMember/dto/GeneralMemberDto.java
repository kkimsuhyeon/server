package com.project.mybnb.generalMember.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneralMemberDto {
    private Long id;
    private String email;
    private String password;
    private String nickname;

}
