package com.project.mybnb.businessMember.controller;

import com.project.mybnb.businessMember.dto.BusinessMemberDto;
import com.project.mybnb.businessMember.dto.RequestSigninBusinessMemberDto;
import com.project.mybnb.businessMember.service.BusinessMemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/business")
public class SignController {

    private final BusinessMemberService businessMemberService;

    @PostMapping(value = "/signin", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?> signinBusinessMember(@ModelAttribute @Valid RequestSigninBusinessMemberDto request) {
        Map<String, String> result = new HashMap<>();

        String token = businessMemberService.signinBusinessMember(BusinessMemberDto.fromRequest(request));
        result.put("token", token);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
