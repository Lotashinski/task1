package com.github.lotashinski.repository.exception;

public final class WrongPasswordException extends RuntimeException {
    public WrongPasswordException(String message) {
        super(message);
    }
}
