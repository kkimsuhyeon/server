package com.project.mybnb.businessMember.service;

import com.project.mybnb.businessMember.domain.BusinessMember;
import com.project.mybnb.businessMember.dto.BusinessMemberDto;
import com.project.mybnb.businessMember.exception.BusinessMemberException;
import com.project.mybnb.businessMember.repository.BusinessMemberRepository;
import com.project.mybnb.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class BusinessMemberService {

    private final BusinessMemberRepository repository;

    @Autowired
    public BusinessMemberService(BusinessMemberRepository repository) {
        this.repository = repository;
    }

    public Map<String, String> signinBusinessMember(BusinessMemberDto dto) {
        Map<String, String> tokens = new HashMap<>();

        BusinessMember member = repository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new BusinessMemberException("BSM001", "해당 이메일을 가진 사람이 존재하지 않습니다"));

        if (!member.getPassword().equals(dto.getPassword())) {
            throw new BusinessMemberException("BSM002", "비밀번호가 틀렸습니다");
        }

        String accessToken = TokenProvider.createToken(member.getId(), member.getEmail(), TokenProvider.ACCESS_TOKEN_EXPIRE_TIME);
        String refreshToken = TokenProvider.createToken(member.getId(), member.getEmail(), TokenProvider.REFRESH_TOKEN_EXPIRE_TIME);

        member.setRefreshToken(refreshToken);

        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);

        return tokens;
    }

    @Transactional
    public void saveBusinessMember(BusinessMemberDto dto) {
        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new BusinessMemberException("BSM003", "같은 이메일을 가진 유저가 존재합니다.");
        } else {
            repository.save(dto.toEntity());
        }
    }

    @Transactional
    public void updateBusinessMember(Long id, BusinessMemberDto dto) {
        BusinessMember result = repository.findById(id)
                .orElseThrow(() -> new BusinessMemberException("BSM001", "해당 id를 가진 유저가 존재하지 않습니다"));

        if (dto.getPassword() != null) result.setPassword(dto.getPassword());
        if (dto.getName() != null) result.setName(dto.getName());
        if (dto.getPassword() != null) result.setPassword(dto.getPassword());
    }

    @Transactional
    public void deleteBusinessMember(Long id) {
        BusinessMember result = repository.findById(id)
                .orElseThrow(() -> new BusinessMemberException("BSM001", "해당 id를 가진 유저가 존재하지 않습니다"));

        result.setExpiredAt(LocalDateTime.now());
    }

    @Transactional
    public String reissueToken(BusinessMemberDto dto) {

        // todo code
        // refreshToken이랑 DB에 들어가있는 refreshToken 비교하는 코드 필요함

        return TokenProvider.createToken(dto.getId(), dto.getEmail(), TokenProvider.ACCESS_TOKEN_EXPIRE_TIME);
    }
}
