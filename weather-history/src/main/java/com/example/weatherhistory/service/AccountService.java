package com.example.weatherhistory.service;

import com.example.weatherhistory.dto.AccountDto;

import java.util.Collection;

public interface AccountService {
    AccountDto findById(Long accountId);

    Collection<AccountDto> searchForAccounts(String firstName, String lastName,
                                             String email, Integer from, Integer size);

    AccountDto update(AccountDto accountDto);

    void deleteById(Long accountId);

    AccountDto registry(AccountDto accountDto);

    Long login(String email, String password);

}
