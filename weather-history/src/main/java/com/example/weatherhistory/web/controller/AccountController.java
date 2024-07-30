package com.example.weatherhistory.web.controller;

import com.example.weatherhistory.dto.AccountDto;
import com.example.weatherhistory.service.AccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@Validated
@RequestMapping(path = "/accounts", produces = "application/json")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/{accountId}")
    @PostAuthorize("#accountId.equals(authentication.principal.getId())")
    public ResponseEntity<AccountDto> findAccountById(@PathVariable("accountId") Long accountId) {
        return new ResponseEntity<>(accountService.findById(accountId), HttpStatus.valueOf(200));
    }

    @GetMapping("/search")
    public ResponseEntity<Collection<AccountDto>> searchForAccounts(
            @RequestParam(name = "firstName", required = false) String firstName,
            @RequestParam(name = "lastName", required = false) String lastName,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "from", required = false, defaultValue = "0") @Min(0) Integer from,
            @RequestParam(name = "size", required = false, defaultValue = "10") @Min(1) Integer size) {

        Collection<AccountDto> accounts = accountService
                .searchForAccounts(firstName, lastName, email, from, size);
        return new ResponseEntity<>(accounts, HttpStatus.valueOf(200));
    }

    @PutMapping(path = "/{accountId}", consumes = "application/json")
    @PreAuthorize("#accountId.equals(authentication.principal.getId())")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable("accountId") @Min(1) Long accountId,
                                                    @RequestBody @Valid AccountDto accountDto) {
        accountDto.setId(accountId);
        return new ResponseEntity<>(accountService.update(accountDto), HttpStatus.valueOf(200));
    }

    //
//
    @DeleteMapping(path = "/{accountId}", consumes = "application/json")
    @ResponseStatus(value = HttpStatus.OK)
    @PreAuthorize("#accountId.equals(authentication.principal.getId())")
    public void deleteAccountById(@PathVariable("accountId") @Min(1) Long accountId) {
        accountService.deleteById(accountId);
    }
}
