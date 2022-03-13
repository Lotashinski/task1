package com.github.lotashinski.repository.exception;

import com.github.lotashinski.exception.HttpException;

public final class WrongPasswordOrUsernotFoundException extends HttpException {
    public WrongPasswordOrUsernotFoundException(String message) {
        super(message);
    }

    @Override
    public int getStatusCode() {
        return 403;
    }
}
