package com.example.weatherhistory.exception.handler;

import com.example.weatherhistory.exception.AccountWithSuchEmailAlreadyExistsException;
import com.example.weatherhistory.exception.IncorrectEmailOrPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
    @ExceptionHandler(AccountWithSuchEmailAlreadyExistsException.class)
    public ResponseEntity<?> onAccountWithThisEmailAlreadyExistsException() {
        return new ResponseEntity<>(HttpStatus.valueOf(409));
    }

    @ExceptionHandler(IncorrectEmailOrPasswordException.class)
    public ResponseEntity<?> onIncorrectEmailOrPasswordException() {
        System.out.println("Incorrect email or password");
        return new ResponseEntity<>(HttpStatus.valueOf(401));
    }
}
