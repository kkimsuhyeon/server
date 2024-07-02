package com.project.mybnb.businessMember.controller;

import com.project.mybnb.businessMember.dto.BusinessMemberDto;
import com.project.mybnb.businessMember.dto.RequestCreateBusinessMemberDto;
import com.project.mybnb.businessMember.dto.RequestUpdateBusinessMemberDto;
import com.project.mybnb.businessMember.service.BusinessMemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/businessMembers")
public class BusinessMemberController {

    private final BusinessMemberService businessMemberService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?> saveBusinessMember(@ModelAttribute @Valid RequestCreateBusinessMemberDto request) {
        businessMemberService.saveBusinessMember(BusinessMemberDto.fromRequest(request));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/{businessMemberId}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?> updateBusinessMember(@PathVariable(value = "businessMemberId") Long id, @ModelAttribute @Valid RequestUpdateBusinessMemberDto request) {
        businessMemberService.updateBusinessMember(id, BusinessMemberDto.fromRequest(request));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{businessMemberId}")
    public ResponseEntity<?> deleteBusinessMember(@PathVariable(value = "businessMemberId") Long id) {
        businessMemberService.deleteBusinessMember(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
