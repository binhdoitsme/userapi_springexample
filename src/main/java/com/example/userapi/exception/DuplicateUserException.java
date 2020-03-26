package com.example.userapi.exception;

/**
 * DuplicateUserException
 */
public class DuplicateUserException extends Throwable {

    private static final long serialVersionUID = -1100960108761365598L;

    public DuplicateUserException() {
        super("An user with the same name exists in database!", null, true, false);
    }    
}