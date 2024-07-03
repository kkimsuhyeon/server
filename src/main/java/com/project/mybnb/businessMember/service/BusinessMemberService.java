package com.project.mybnb.businessMember.service;

import com.project.mybnb.businessMember.domain.BusinessMember;
import com.project.mybnb.businessMember.dto.BusinessMemberDto;
import com.project.mybnb.businessMember.repository.BusinessMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional(readOnly = true)
public class BusinessMemberService {

    private final BusinessMemberRepository repository;

    @Autowired
    public BusinessMemberService(BusinessMemberRepository repository) {
        this.repository = repository;
    }

    public String signinBusinessMember(BusinessMemberDto dto) {
        BusinessMember member = repository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("해당 이메일을 가진 유저가 존재하지 않습니다."));

        if (!member.getPassword().equals(dto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다");
        }

        return "token";
    }

    @Transactional
    public void saveBusinessMember(BusinessMemberDto dto) {
        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("같은 이메일을 가진 유저가 존재합니다.");
        } else {
            repository.save(dto.toEntity());
        }
    }

    @Transactional
    public void updateBusinessMember(Long id, BusinessMemberDto dto) {
        BusinessMember result = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id를 가진 유저가 존재하지 않습니다"));

        if (dto.getPassword() != null) result.setPassword(dto.getPassword());
        if (dto.getName() != null) result.setName(dto.getName());
        if (dto.getPassword() != null) result.setPassword(dto.getPassword());
    }

    @Transactional
    public void deleteBusinessMember(Long id) {
        BusinessMember result = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id를 가진 유저가 존재하지 않습니다"));

        result.setExpiredAt(LocalDateTime.now());
    }

}
