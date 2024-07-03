package com.project.mybnb.generalMember.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping(value = "/join")
    public String join() {
        return "join";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }
}
