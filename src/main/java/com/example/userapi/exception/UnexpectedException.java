package com.example.userapi.exception;

public class UnexpectedException extends Throwable {
    private static final long serialVersionUID = -164683817473157749L;

    public UnexpectedException() {
        super("An unexpected exception occured!", null, true, false);
    }
}