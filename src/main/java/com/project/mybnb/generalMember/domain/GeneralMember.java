package com.project.mybnb.generalMember.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class GeneralMember {
    @Id // 기본키로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 1씩 증가
    @Column(name = "id")
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nickname")
    private String nickname;

    @Builder // Builder 패턴으로 객체 생성
    public GeneralMember(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }
}


