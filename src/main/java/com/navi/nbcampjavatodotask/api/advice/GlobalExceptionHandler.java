package com.navi.nbcampjavatodotask.api.advice;

import com.navi.nbcampjavatodotask.api.model.exception.ErrorResponse;
import com.navi.nbcampjavatodotask.api.model.exception.TodoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler()
    public ResponseEntity<ErrorResponse> handlerRuntimeException(
            RuntimeException exception
          // exception 객체를 파라미터로 받는다
    ){
        var message = exception.getMessage();
        var errorResponse = new  ErrorResponse(
                "RUNTIME_EXCEPTION",
                "일시적 오류입니다",
                message
        );
        return ResponseEntity.internalServerError().body(errorResponse);
    }



    @ExceptionHandler()
    public ResponseEntity<ErrorResponse> handleTodoException(
            TodoException exception
    ){
        var errorResponse = new ErrorResponse(
                exception.getErrorCode(),
                exception.getErrorMessage(),
                exception.getErrorMessageForDev()
        );
        return ResponseEntity.status(500).body(errorResponse);
    }
}
