package com.project.mybnb.generalMember.controller;

import com.project.mybnb.businessMember.dto.BusinessMemberDto;
import com.project.mybnb.businessMember.dto.request.RequestSignupBusinessMemberDto;
import com.project.mybnb.generalMember.GeneralMemberCreateForm;
import com.project.mybnb.generalMember.dto.GeneralMemberDto;
import com.project.mybnb.generalMember.service.GeneralMemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final GeneralMemberService memberService;
    private final GeneralMemberService generalMemberService;

    @PostMapping(value = "/signup", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?> signupGeneralMember(@ModelAttribute GeneralMemberDto request) {
        generalMemberService.create(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
