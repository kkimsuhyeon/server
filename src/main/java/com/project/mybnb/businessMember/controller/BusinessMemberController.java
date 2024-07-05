package com.project.mybnb.businessMember.controller;

import com.project.mybnb.businessMember.dto.BusinessMemberDto;
import com.project.mybnb.businessMember.dto.BusinessMemberWithArticleDto;
import com.project.mybnb.businessMember.dto.request.RequestUpdateBusinessMemberDto;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/business")
public class BusinessMemberController {

    private final BusinessMemberService businessMemberService;

    @Operation(security = {@SecurityRequirement(name = "Authorization")})
    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?> updateBusinessMember(@AuthenticationPrincipal MemberPrinciple principle, @ModelAttribute @Valid RequestUpdateBusinessMemberDto request) {
        businessMemberService.updateBusinessMember(principle.id(), BusinessMemberDto.fromRequest(request));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(security = {@SecurityRequirement(name = "Authorization")})
    @DeleteMapping()
    public ResponseEntity<?> deleteBusinessMember(@AuthenticationPrincipal MemberPrinciple principle) {
        businessMemberService.deleteBusinessMember(principle.id());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
