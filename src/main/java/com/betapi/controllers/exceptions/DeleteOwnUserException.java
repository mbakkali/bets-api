package com.betapi.controllers.exceptions;

public class DeleteOwnUserException extends Exception {
    public DeleteOwnUserException(String errorMessage) {
        super(errorMessage);
    }
}
