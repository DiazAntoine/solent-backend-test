package com.diaz.mowitnow.exception;

/**
 * Exception thrown when the input file cannot be parsed correctly.
 */
public class InvalidInputException extends Exception {

    public InvalidInputException(String message) {
        super(message);
    }
}
