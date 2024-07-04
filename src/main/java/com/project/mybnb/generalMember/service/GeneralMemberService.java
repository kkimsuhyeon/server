package com.project.mybnb.generalMember.service;

import com.project.mybnb.generalMember.domain.GeneralMember;
import com.project.mybnb.generalMember.dto.GeneralMemberDto;
import com.project.mybnb.generalMember.repository.GeneralMemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Transactional
public class GeneralMemberService {
    private final GeneralMemberRepository repository;
    private final PasswordEncoder passwordEncoder;

    public GeneralMember create(GeneralMemberDto dto) {
        GeneralMember member = new GeneralMember();

        member.setEmail(dto.getEmail());
        member.setPassword(passwordEncoder.encode(dto.getPassword()));
        member.setNickname(dto.getNickname());
        this.repository.save(member);
        return member;
    }
}
