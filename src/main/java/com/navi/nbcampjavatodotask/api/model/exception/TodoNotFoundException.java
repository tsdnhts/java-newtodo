package com.navi.nbcampjavatodotask.api.model.exception;

public class TodoNotFoundException extends TodoException {
    public TodoNotFoundException(){
        super("TODO_NOT_FOUND", "Todo Not Found", "Todo Entity Not Found");
    }
}
