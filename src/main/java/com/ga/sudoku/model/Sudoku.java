package com.ga.sudoku.model;

import com.ga.sudoku.exception.InvalidCharacterException;
import com.ga.sudoku.exception.SudokuFileNotFoundException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

// Model | Sudoku puzzle: load from file, save solution, print board. Uses SudokuBoard for the grid.

public class Sudoku {

    private SudokuBoard board;

    public Sudoku() {
        this.board = new SudokuBoard();
    }

    public SudokuBoard getBoard() {
        return board;
    }

    // Reads puzzle file, fills board. 9 lines, 9 digits per line (0–9). Non-zero = fixed (clues). Throws SudokuFileNotFoundException | InvalidCharacterException.
    public void loadFromFile(String path) {
        Path file = Paths.get(path);
        if (!Files.exists(file)) {
            throw new SudokuFileNotFoundException("Puzzle file not found: " + path);
        }
        List<String> lines;
        try {
            lines = Files.readAllLines(file);
        } catch (IOException e) {
            throw new SudokuFileNotFoundException("Cannot read puzzle file: " + path, e);
        }
        if (lines.size() != SudokuBoard.SIZE) {
            throw new InvalidCharacterException("Expected 9 lines, got " + lines.size());
        }
        this.board = new SudokuBoard();
        for (int r = 0; r < SudokuBoard.SIZE; r++) {
            String line = lines.get(r);
            String[] parts = line.trim().split("\\s+");
            if (parts.length != SudokuBoard.SIZE) {
                throw new InvalidCharacterException("Line " + (r + 1) + ": expected 9 values, got " + parts.length);
            }
            for (int c = 0; c < SudokuBoard.SIZE; c++) {
                int value = parseDigit(parts[c], r + 1, c + 1);
                board.setValue(r, c, value);
                if (value != 0) {
                    board.setFixed(r, c, true);
                }
            }
        }
    }

    // Validates token is single digit 0–9 | throws InvalidCharacterException if not.
    private static int parseDigit(String token, int lineNum, int colNum) {
        if (token == null || token.length() != 1) {
            throw new InvalidCharacterException("Invalid token at line " + lineNum + ", column " + colNum + ": '" + token + "' (expected single digit 0-9)");
        }
        char ch = token.charAt(0);
        if (ch < '0' || ch > '9') {
            throw new InvalidCharacterException("Invalid character at line " + lineNum + ", column " + colNum + ": '" + ch + "' (expected digit 0-9)");
        }
        return ch - '0';
    }

    // Saves current board to file. Input puzzle1.txt -> output puzzle1_solution.txt (base + _solution.txt).
    public void saveSolution(String inputPath) {
        String base = inputPath.endsWith(".txt") ? inputPath.replaceAll("\\.txt$", "") : inputPath;
        String outputPath = base + "_solution.txt";
        StringBuilder sb = new StringBuilder();
        for (int r = 0; r < SudokuBoard.SIZE; r++) {
            for (int c = 0; c < SudokuBoard.SIZE; c++) {
                if (c > 0) sb.append(' ');
                sb.append(board.getValue(r, c));
            }
            sb.append(System.lineSeparator());
        }
        try {
            Files.writeString(Paths.get(outputPath), sb.toString());
        } catch (IOException e) {
            throw new RuntimeException("Failed to write solution file: " + outputPath, e);
        }
    }

    // Prints board in Game Play format (title, rows, separators). Title e.g. "Puzzle 1" or "Puzzle 1 Solved".
    public void printBoard(String title) {
        System.out.println("\t\t" + title);
        for (int r = 0; r < SudokuBoard.SIZE; r++) {
            System.out.println(formatRow(r));
            if (r == 2 || r == 5) {
                System.out.println("----------------------------------");
            }
        }
        System.out.println();
    }

    // One row: digits with "  |  " between groups of 3 columns.
    private String formatRow(int row) {
        StringBuilder sb = new StringBuilder();
        for (int c = 0; c < SudokuBoard.SIZE; c++) {
            if (c > 0) {
                sb.append(c % 3 == 0 ? "  |  " : "  ");
            }
            sb.append(board.getValue(row, c));
        }
        return sb.toString();
    }
}
