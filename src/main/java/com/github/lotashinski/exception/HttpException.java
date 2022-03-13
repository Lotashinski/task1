package com.github.lotashinski.exception;

public class HttpException extends RuntimeException {
    public int getStatusCode(){
        return 500;
    }

    public HttpException() {
        super();
    }

    public HttpException(String message) {
        super(message);
    }
}
