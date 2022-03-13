package com.github.lotashinski.repository.exception;

import com.github.lotashinski.exception.HttpException;

public final class LoginAlreadyExistException extends HttpException {
    public LoginAlreadyExistException(String message) {
        super(message);
    }

    @Override
    public int getStatusCode() {
        return 409;
    }
}
