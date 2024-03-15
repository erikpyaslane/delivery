package com.food.delivery.exceptions;

public class RecourceNotFoundException extends Exception{

    private final String message;

    public RecourceNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
