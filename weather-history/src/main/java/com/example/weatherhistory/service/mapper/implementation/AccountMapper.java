package com.example.weatherhistory.service.mapper.implementation;

import com.example.weatherhistory.dto.AccountDto;
import com.example.weatherhistory.entity.Account;
import com.example.weatherhistory.service.mapper.DefaultMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper extends DefaultMapper<Account, AccountDto> {

    @Autowired
    public AccountMapper(ModelMapper mapper) {
        super(mapper, Account.class, AccountDto.class);
    }
}
