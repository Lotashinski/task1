package com.github.lotashinski.repository.exception;

import com.github.lotashinski.exception.HttpException;

public final class NotFoundException extends HttpException {
    public NotFoundException(String message) {
        super(message);
    }

    @Override
    public int getStatusCode() {
        return 404;
    }
}
