package com.betapi.controllers.exceptions;

public class BetOwnerNotFoundException extends Exception {
    public BetOwnerNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
