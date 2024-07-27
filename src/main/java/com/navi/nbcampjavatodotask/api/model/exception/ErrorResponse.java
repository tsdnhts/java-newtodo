package com.navi.nbcampjavatodotask.api.model.exception;

public record ErrorResponse(
        String errorCode,
        String errorMessage,
        String errorMessageForDev
) {

}
