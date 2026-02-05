package com.ga.sudoku.exception;


// throws exception when puzzle file contains any char that is not a digit (a digit 0–9 or allowed whitespace)
public class InvalidCharacterException extends RuntimeException {

    public InvalidCharacterException(String message) {
        super(message);
    }

    public InvalidCharacterException(String message, Throwable cause) {
        super(message, cause);
    }
}
