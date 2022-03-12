package com.github.lotashinski.repository.exception;

public final class LoginAlreadyExistException extends RuntimeException {
    public LoginAlreadyExistException(String message) {
        super(message);
    }
}
