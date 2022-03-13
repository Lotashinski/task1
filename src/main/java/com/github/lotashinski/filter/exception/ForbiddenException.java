package com.github.lotashinski.filter.exception;

import com.github.lotashinski.exception.HttpException;

public final class ForbiddenException extends HttpException {
    public ForbiddenException(String message) {
        super(message);
    }

    @Override
    public int getStatusCode() {
        return 403;
    }
}
