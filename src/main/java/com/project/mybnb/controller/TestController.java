package com.project.mybnb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping(value = "/user")
    public ResponseEntity<?> checkUser() {
        return new ResponseEntity<>("user", HttpStatus.OK);
    }

    @GetMapping(value = "/manager")
    public ResponseEntity<?> checkManager() {
        return new ResponseEntity<>("manager", HttpStatus.OK);

    }
}
