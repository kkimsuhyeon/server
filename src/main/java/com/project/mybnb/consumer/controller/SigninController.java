package com.project.mybnb.consumer.controller;

import com.project.mybnb.consumer.dto.ConsumerDto;
import com.project.mybnb.consumer.dto.request.RequestSigninConsumerDto;
import com.project.mybnb.consumer.dto.request.RequestSignupConsumerDto;
import com.project.mybnb.consumer.service.ConsumerService;
import com.project.mybnb.security.MemberPrinciple;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/consumer")
public class SigninController {

    private final ConsumerService consumerService;

    @PostMapping(value = "/signin", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?> signinConsumer(@ModelAttribute @Valid RequestSigninConsumerDto request) {
        Map<String, Object> result = new HashMap<>();

        Map<String, String> tokens = consumerService.signinConsumer(ConsumerDto.fromRequest(request));
        result.put("data", tokens);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/signup", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?> signupConsumer(@ModelAttribute @Valid RequestSignupConsumerDto request) {
        consumerService.saveConsumer(ConsumerDto.fromRequest(request));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping(value = "/refreshToken")
    public ResponseEntity<?> reissueToken(@RequestHeader("Authorization") String refreshToken, @AuthenticationPrincipal MemberPrinciple principle) {
        String token = consumerService.reissueToken(refreshToken, principle.toConsumerDto());
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
