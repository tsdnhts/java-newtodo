package com.navi.nbcampjavatodotask.api.model.exception;

import lombok.Getter;

@Getter
public abstract class TodoException extends RuntimeException {
    private final String  errorCode;
    private final String  errorMessage;
    private final String  errorMessageForDev;
    // private fianl로 선언을 했기 때문에, getter를 통해서 꺼내야 한다.

    protected TodoException(String errorCode, String errorMessage, String errorMessageForDev){
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorMessageForDev = errorMessageForDev;
    }
}
