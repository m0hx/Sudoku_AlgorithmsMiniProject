package com.ga.sudoku.controller;

import com.ga.sudoku.exception.InvalidCharacterException;
import com.ga.sudoku.exception.SudokuFileNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

// Controller | Returns JSON error responses when file missing or invalid chars (no Whitelabel page).
@RestControllerAdvice
public class GlobalExceptionHandler {

    // File not found -> 404 + JSON (error, message, status).
    @ExceptionHandler(SudokuFileNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleFileNotFound(SudokuFileNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                        "error", "Puzzle file not found",
                        "message", ex.getMessage(),
                        "status", 404
                ));
    }

    // Invalid character in puzzle file -> 400 + JSON (error, message, status).
    @ExceptionHandler(InvalidCharacterException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidCharacter(InvalidCharacterException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "error", "Invalid character in puzzle file",
                        "message", ex.getMessage(),
                        "status", 400
                ));
    }
}
