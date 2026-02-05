package com.ga.sudoku.model;

// Model Represents a single cell on the sudoku board
// Value 0 = empty |  1–9 = filled.  | Fixed = true // for initial clues (not editable).


public class SudokuCell {

    private final int row;
    private final int col;
    private int value;
    private final boolean fixed;

    public SudokuCell(int row, int col, int value, boolean fixed) {
        this.row = row;
        this.col = col;
        this.value = value;
        this.fixed = fixed;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isFixed() {
        return fixed;
    }
}
