package com.github.lotashinski.service.exception;

import com.github.lotashinski.exception.HttpException;

public final class LogicException  extends HttpException {
    public LogicException(String message) {
        super(message);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
