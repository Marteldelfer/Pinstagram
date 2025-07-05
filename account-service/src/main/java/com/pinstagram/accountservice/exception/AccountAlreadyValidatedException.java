package com.pinstagram.accountservice.exception;

public class AccountAlreadyValidatedException extends RuntimeException {
    public AccountAlreadyValidatedException(String message) {
        super(message);
    }
}
