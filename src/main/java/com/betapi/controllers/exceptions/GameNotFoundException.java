package com.betapi.controllers.exceptions;

public class GameNotFoundException extends Exception {
    public GameNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
