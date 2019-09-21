package com.example.zuulserver.exception;

/**
 * 限流异常
 */
public class RateLimitException extends RuntimeException {

    private int code;
    private String message;

    public RateLimitException() {
    }

    public RateLimitException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "RateLimitException{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
