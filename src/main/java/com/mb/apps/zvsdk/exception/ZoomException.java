package com.mb.apps.zvsdk.exception;

import org.springframework.validation.Errors;

import java.util.Map;

public class ZoomException extends RuntimeException {
    private final String errorInfo;

    public ZoomException(String message, String errorInfo) {

        this.errorInfo = errorInfo;
    }

    public ZoomException(String message, Throwable cause, String errorInfo) {
        super(message, cause);
        this.errorInfo = errorInfo;
    }
}
