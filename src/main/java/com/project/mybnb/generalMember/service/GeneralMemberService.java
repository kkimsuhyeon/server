package com.project.mybnb.generalMember.service;

import com.project.mybnb.generalMember.GeneralMember;
import com.project.mybnb.generalMember.repository.GeneralMemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GeneralMemberService {
    private final GeneralMemberRepository repository;
    private final PasswordEncoder passwordEncoder;

    public GeneralMemberService(GeneralMemberRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public GeneralMember create(String email, String password, String nickname) {
        GeneralMember member = new GeneralMember();

        member.setEmail(email);
        member.setPassword(passwordEncoder.encode(password));
        member.setNickname(nickname);
        this.repository.save(member);
        return member;
    }
}
