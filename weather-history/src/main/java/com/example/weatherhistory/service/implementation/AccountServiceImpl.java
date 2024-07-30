package com.example.weatherhistory.service.implementation;

import com.example.weatherhistory.dao.repository.AccountRepository;
import com.example.weatherhistory.dto.AccountDto;
import com.example.weatherhistory.entity.Account;
import com.example.weatherhistory.exception.AccountWithSuchEmailAlreadyExistsException;
import com.example.weatherhistory.exception.IncorrectEmailOrPasswordException;
import com.example.weatherhistory.service.AccountService;
import com.example.weatherhistory.service.mapper.implementation.AccountMapper;
import com.example.weatherhistory.util.OffsetPageRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.example.weatherhistory.dao.specification.AccountSpecificationFactory.*;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccountMapper mapper;

    @Override
    public AccountDto findById(@Min(1) Long accountId) {
        return mapper.toDto(accountRepository.findById(accountId).orElseThrow(NoSuchElementException::new));
    }

    @Override
    public Collection<AccountDto> searchForAccounts(String firstName, String lastName,
                                                    String email, @Min(0) Integer from, @Min(1) Integer size) {
        OffsetPageRequest pageRequest =
                new OffsetPageRequest(from, size, Sort.by("id").ascending());
        Specification<Account> specifications = Specification.where(
                firstNameLike(firstName)
                        .and(lastNameLike(lastName))
                        .and(emailLike(email)));

        return accountRepository.findAll(specifications, pageRequest)
                .getContent()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AccountDto update(AccountDto accountDto) {
        Account updatedAccount = mapper.toEntity(accountDto);
        updatedAccount.setPassword(passwordEncoder.encode(accountDto.getPassword()));

        try {
            return mapper.toDto(accountRepository.save(updatedAccount));
        } catch (DataIntegrityViolationException e) {
            throw new AccountWithSuchEmailAlreadyExistsException();
        }
    }

    @Override
    public void deleteById(Long accountId) {
        try {
            accountRepository.deleteById(accountId);
        } catch (DataIntegrityViolationException e) {
            throw new NoSuchElementException();
        }
    }

    @Override
    @Transactional
    public AccountDto registry(@Valid AccountDto accountDto) {
        Account newAccount = mapper.toEntity(accountDto);
        newAccount.setPassword(passwordEncoder.encode(newAccount.getPassword()));


        if(accountRepository.findByEmail(accountDto.getEmail()).isPresent()) {
            throw new AccountWithSuchEmailAlreadyExistsException();
        }

        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
        try {
            return mapper.toDto(accountRepository.save(newAccount));
        } catch (DataIntegrityViolationException exception) {
            throw new AccountWithSuchEmailAlreadyExistsException();
        }
    }


    @Override
    @Transactional
    public Long login(String email, String password) {
        Account account = accountRepository.findByEmail(email).orElseThrow(IncorrectEmailOrPasswordException::new);
//        System.out.println(SecurityContextHolder.getContext().getAuthentication().isAuthenticated());

        if (!passwordEncoder.matches(password, account.getPassword())) {
            throw new IncorrectEmailOrPasswordException();
        }
////        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(true);
//
//        UsernamePasswordAuthenticationToken authenticationToken =
//                new UsernamePasswordAuthenticationToken(account, null, account.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//
//        System.out.println(SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
        return account.getId();
    }
}
