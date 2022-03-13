package com.github.lotashinski.service.exception;

import com.github.lotashinski.exception.HttpException;

public final class RequestBodyConvertException extends HttpException {
    public RequestBodyConvertException(String message) {
        super(message);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }
}
