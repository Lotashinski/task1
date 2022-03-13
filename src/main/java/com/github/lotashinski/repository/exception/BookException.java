package com.github.lotashinski.repository.exception;

import com.github.lotashinski.exception.HttpException;

public final class BookException extends HttpException {
    public BookException(String message) {
        super(message);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
