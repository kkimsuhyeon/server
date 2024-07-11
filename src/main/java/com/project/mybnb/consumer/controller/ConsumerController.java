package com.project.mybnb.consumer.controller;

import com.project.mybnb.consumer.dto.ConsumerDto;
import com.project.mybnb.consumer.dto.request.RequestUpdateConsumerDto;
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

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/consumer")
public class ConsumerController {
    private final ConsumerService consumerService;

    @Operation(security = {@SecurityRequirement(name = "Authorization")})
    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public ResponseEntity<?> updateConsumerMember(@AuthenticationPrincipal MemberPrinciple principle, @ModelAttribute @Valid RequestUpdateConsumerDto request) {
        consumerService.updateConsumer(principle.id(), ConsumerDto.fromRequest(request));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(security = {@SecurityRequirement(name = "Authorization")})
    @DeleteMapping()
    public ResponseEntity<?> deleteConsumerMember(@AuthenticationPrincipal MemberPrinciple principle) {
        consumerService.deleteConsumer(principle.id());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
