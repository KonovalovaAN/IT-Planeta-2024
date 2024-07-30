package com.example.weatherhistory.web.controller;

import com.example.weatherhistory.dto.AccountDto;
import com.example.weatherhistory.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/login", produces = "application/json")
@RequiredArgsConstructor
public class LoginController {
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<Long> login(@RequestBody AccountDto accountDto) {
        return new ResponseEntity<>(accountService.login(accountDto.getEmail(), accountDto.getPassword()), HttpStatus.valueOf(200));
    }
}
