package com.project.mybnb.businessMember.controller;

import com.project.mybnb.businessMember.dto.BusinessMemberDto;
import com.project.mybnb.businessMember.dto.request.RequestSigninBusinessMemberDto;
import com.project.mybnb.businessMember.dto.request.RequestSignupBusinessMemberDto;
import com.project.mybnb.businessMember.service.BusinessMemberService;
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
@RequestMapping("/api/business")
public class SignController {

    private final BusinessMemberService businessMemberService;

    @PostMapping(value = "/signin", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?> signinBusinessMember(@ModelAttribute @Valid RequestSigninBusinessMemberDto request) {
        Map<String, Object> result = new HashMap<>();

        Map<String, String> tokens = businessMemberService.signinBusinessMember(BusinessMemberDto.fromRequest(request));
        result.put("data", tokens);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/signup", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?> signupBusinessMember(@ModelAttribute @Valid RequestSignupBusinessMemberDto request) {
        businessMemberService.saveBusinessMember(BusinessMemberDto.fromRequest(request));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(security = {@SecurityRequirement(name = "Authorization")})
    @GetMapping(value = "/refreshToken")
    public ResponseEntity<?> reissueToken(@RequestHeader("Authorization") String refreshToken, @AuthenticationPrincipal MemberPrinciple principle) {
        String token = businessMemberService.reissueToken(refreshToken, principle.toDto());
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
}
