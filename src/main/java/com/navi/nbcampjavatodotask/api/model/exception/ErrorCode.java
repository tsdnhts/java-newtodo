package com.navi.nbcampjavatodotask.api.model.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "", "");
    private final HttpStatus httpStatus;
    private final String errorMessage;
    private final String errorMessageForDev;

    ErrorCode(HttpStatus httpStatus, String errorMessage, String errorMessageForDev) {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
        this.errorMessageForDev = errorMessageForDev;
    }
}
