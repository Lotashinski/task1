package com.github.lotashinski.repository.exception;

import com.github.lotashinski.exception.HttpException;

public final class WrongPasswordException extends HttpException {
    public WrongPasswordException(String message) {
        super(message);
    }

    @Override
    public int getStatusCode() {
        return 403;
    }
}
