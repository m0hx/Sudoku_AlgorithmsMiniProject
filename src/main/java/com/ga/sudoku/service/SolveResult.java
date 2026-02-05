package com.ga.sudoku.service;

// Service | Result of a solve attempt: solved (yes/no) | solve time in ms.
public record SolveResult(boolean solved, long solveTimeMs) {

    // Returns solve time as seconds string e.g. "0.05".
    public String getSolveTimeSeconds() {
        return String.format("%.2f", solveTimeMs / 1000.0);
    }
}
