package com.ga.sudoku.exception;

// throws exception if the puzzle file doesn't exist
public class SudokuFileNotFoundException extends RuntimeException {

    public SudokuFileNotFoundException(String message) {
        super(message);
    }

    public SudokuFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
