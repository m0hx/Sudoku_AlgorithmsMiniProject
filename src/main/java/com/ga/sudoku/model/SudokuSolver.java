package com.ga.sudoku.model;

// Model | Solves puzzle using backtracking. Only fills empty cells (value 0) | does not modify fixed clues.

public class SudokuSolver {

    // Solves puzzle in place. Returns true if solution found, false otherwise.
    public boolean solve(SudokuBoard board) {
        int[] empty = findEmptyCell(board);
        if (empty == null) {
            return true; // no empty cell, board is full and valid
        }
        int row = empty[0];
        int col = empty[1];
        for (int num = 1; num <= SudokuBoard.SIZE; num++) {
            if (board.isValidMove(row, col, num)) {
                board.setValue(row, col, num);
                if (solve(board)) {
                    return true;
                }
                board.setValue(row, col, 0); // backtrack
            }
        }
        return false;
    }

    // Returns [row, col] of first cell with value 0, or null if none.
    private int[] findEmptyCell(SudokuBoard board) {
        for (int r = 0; r < SudokuBoard.SIZE; r++) {
            for (int c = 0; c < SudokuBoard.SIZE; c++) {
                if (board.getValue(r, c) == 0) {
                    return new int[]{r, c};
                }
            }
        }
        return null;
    }
}
