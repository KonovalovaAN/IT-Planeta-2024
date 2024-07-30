package com.example.weatherhistory.web.controller;

import com.example.weatherhistory.dto.AccountDto;
import com.example.weatherhistory.service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/registration", produces = "application/json")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AccountService accountService;

    @PostMapping(consumes = "application/json")
    @Operation(
            summary = "Registering a new account",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Success"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = """
                                    firstName = null,\s
                                    firstName = "" or consists of spaces,\s
                                    lastName = null,\s
                                    lastName = "" or consists of spaces,\s
                                    email = null,
                                    email = "" or consists of spaces,\s
                                    account email is not valid,\s
                                    password = null, password = "" or consists of spaces,\s
                                    invalid authorization data"""
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Request from authorized account"
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Account with such email already exists"
                    )
            }
    )
    public ResponseEntity<AccountDto> registry(@RequestBody @Valid AccountDto accountDto) {
        return new ResponseEntity<>(accountService.registry(accountDto), HttpStatus.valueOf(201));
    }
}
