package com.example.userapi.exception;

import java.util.List;

/**
 * InvalidFieldInputException
 */
public class InvalidFieldInputException extends Throwable {

    private static final long serialVersionUID = -5512185899025481539L;

    public InvalidFieldInputException(List<String> invalidFields) {
        super("Invalid field(s): " + invalidFields.toString().replace("[", "").replace("]", ""), null, true, false);
    }
}