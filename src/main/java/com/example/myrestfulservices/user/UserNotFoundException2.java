package com.example.myrestfulservices.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INSUFFICIENT_STORAGE)
public class UserNotFoundException2 extends RuntimeException {
    public UserNotFoundException2(String message) {
        super(message);
    }

    public UserNotFoundException2(int message) {
        super(String.valueOf(message));
    }
}
