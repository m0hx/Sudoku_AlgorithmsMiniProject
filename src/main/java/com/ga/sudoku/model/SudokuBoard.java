package com.ga.sudoku.model;

// Model | 9×9 sudoku board. Holds cells and validates moves (row, column, 3×3 sub-grid).

public class SudokuBoard {

    public static final int SIZE = 9;
    public static final int BOX_SIZE = 3;

    private final SudokuCell[][] grid;

    // Creates an empty board (all cells 0, not fixed).
    public SudokuBoard() {
        this.grid = new SudokuCell[SIZE][SIZE];
        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                grid[r][c] = new SudokuCell(r, c, 0, false);
            }
        }
    }

    public SudokuCell getCell(int row, int col) {
        checkBounds(row, col);
        return grid[row][col];
    }

    public int getValue(int row, int col) {
        return getCell(row, col).getValue();
    }

    // Sets value at (row, col). Rejects value not 0–9 | rejects overwriting fixed (clue) cell.
    public void setValue(int row, int col, int value) {
        checkBounds(row, col);
        if (value < 0 || value > 9) {
            throw new IllegalArgumentException("Value must be 0–9, got: " + value);
        }
        if (grid[row][col].isFixed()) {
            throw new IllegalArgumentException("Cannot overwrite fixed cell at (" + row + "," + col + ")");
        }
        grid[row][col].setValue(value);
    }

    // Marks cell as fixed (initial clue). Called during load | bypasses setValue validation.
    public void setFixed(int row, int col, boolean fixed) {
        checkBounds(row, col);
        int value = grid[row][col].getValue();
        grid[row][col] = new SudokuCell(row, col, value, fixed);
    }

    // Returns true if placing num at (row, col) is valid | not in same row, column, or 3×3 sub-grid.
    public boolean isValidMove(int row, int col, int num) {
        checkBounds(row, col);
        if (num < 1 || num > 9) {
            return false;
        }
        // check row
        for (int c = 0; c < SIZE; c++) {
            if (c != col && grid[row][c].getValue() == num) return false;
        }
        // check column
        for (int r = 0; r < SIZE; r++) {
            if (r != row && grid[r][col].getValue() == num) return false;
        }
        // check 3×3 sub-grid
        int boxRow = (row / BOX_SIZE) * BOX_SIZE;
        int boxCol = (col / BOX_SIZE) * BOX_SIZE;
        for (int r = boxRow; r < boxRow + BOX_SIZE; r++) {
            for (int c = boxCol; c < boxCol + BOX_SIZE; c++) {
                if ((r != row || c != col) && grid[r][c].getValue() == num) return false;
            }
        }
        return true;
    }

    private void checkBounds(int row, int col) {
        if (row < 0 || row >= SIZE || col < 0 || col >= SIZE) {
            throw new IllegalArgumentException("Row and col must be 0..8, got (" + row + "," + col + ")");
        }
    }
}
