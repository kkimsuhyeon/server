package com.project.mybnb.consumer.service;


import com.project.mybnb.consumer.domain.Consumer;
import com.project.mybnb.consumer.dto.ConsumerDto;
import com.project.mybnb.consumer.exception.ConsumerException;
import com.project.mybnb.consumer.repository.ConsumerRepository;
import com.project.mybnb.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional(readOnly = true)
public class ConsumerService {
    private final ConsumerRepository repository;

    @Autowired
    public ConsumerService(ConsumerRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Map<String, String> signinConsumer(ConsumerDto dto) {
        Map<String, String> tokens = new HashMap<>();

        Consumer consumer = repository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new ConsumerException("BSM001", "해당 이메일을 가진 사람이 존재하지 않습니다"));

        if (!consumer.getPassword().equals(dto.getPassword())) {
            throw new ConsumerException("BSM002", "비밀번호가 틀렸습니다");
        }

        String accessToken = TokenProvider.createToken(consumer.getId(), consumer.getEmail(), TokenProvider.ACCESS_TOKEN_EXPIRE_TIME);
        String refreshToken = TokenProvider.createToken(consumer.getId(), consumer.getEmail(), TokenProvider.REFRESH_TOKEN_EXPIRE_TIME);

        consumer.setRefreshToken(refreshToken);

        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);

        return tokens;
    }

    @Transactional
    public void saveConsumer(ConsumerDto dto) {
        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new ConsumerException("BSM003", "같은 이메일을 가진 유저가 존재합니다.");
        } else {
            repository.save(dto.toEntity());
        }
    }

    @Transactional
    public void updateConsumer(Long id, ConsumerDto dto) {
        Consumer result = repository.findById(id)
                .orElseThrow(() -> new ConsumerException("BSM001", "해당 id를 가진 유저가 존재하지 않습니다"));

        if (dto.getPassword() != null) result.setPassword(dto.getPassword());
        if (dto.getName() != null) result.setName(dto.getName());
    }

    @Transactional
    public void deleteConsumer(Long id) {
        Consumer result = repository.findById(id)
                .orElseThrow(() -> new ConsumerException("BSM001", "해당 id를 가진 유저가 존재하지 않습니다"));

        result.setExpiredAt(LocalDateTime.now());
    }

    @Transactional
    public String reissueToken(String refreshToken, ConsumerDto dto) {

        Consumer result = repository.findByEmail(dto.getEmail()).orElseThrow(() ->
                new ConsumerException("BSM001", "해당 id를 가진 유저가 존재하지 않습니다"));

        if (result.getRefreshToken().equals(refreshToken)) {
            return TokenProvider.createToken(dto.getId(), dto.getEmail(), TokenProvider.ACCESS_TOKEN_EXPIRE_TIME);
        } else {
            throw new ConsumerException("BSM015", "토큰이 바뀌었습니다??");
        }
    }
}
