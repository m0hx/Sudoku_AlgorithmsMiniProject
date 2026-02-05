package com.ga.sudoku.service;

import com.ga.sudoku.model.Sudoku;
import com.ga.sudoku.model.SudokuBoard;
import com.ga.sudoku.model.SudokuSolver;
import org.springframework.stereotype.Service;

// Service | Loads puzzle, solves, saves solution, prints board. Timer around solve step.
@Service
public class SudokuService {

    private final SudokuSolver solver = new SudokuSolver();

    // fileParam e.g. "puzzle1" or "puzzle1.txt". Load, print, solve, print solved, save. Returns SolveResult (solved + solveTimeMs).
    public SolveResult solvePuzzle(String fileParam) {
        String path = fileParam.endsWith(".txt") ? fileParam : fileParam + ".txt";
        Sudoku sudoku = new Sudoku();
        sudoku.loadFromFile(path);
        String title = toTitle(path);
        sudoku.printBoard(title);
        SudokuBoard board = sudoku.getBoard();
        long startMs = System.currentTimeMillis();
        boolean solved = solver.solve(board);
        long solveTimeMs = System.currentTimeMillis() - startMs;
        if (solved) {
            sudoku.printBoard(title + " Solved");
            sudoku.saveSolution(path);
            System.out.println("Solved in " + String.format("%.2f", solveTimeMs / 1000.0) + " seconds.");
        }
        return new SolveResult(solved, solveTimeMs);
    }

    // Path to title e.g. puzzle1.txt -> "Puzzle 1", puzzle2.txt -> "Puzzle 2".
    private String toTitle(String path) {
        String base = path.endsWith(".txt") ? path.substring(0, path.length() - 4) : path;
        if (base.toLowerCase().startsWith("puzzle")) {
            return "Puzzle " + base.substring(6).trim();
        }
        return base;
    }
}
