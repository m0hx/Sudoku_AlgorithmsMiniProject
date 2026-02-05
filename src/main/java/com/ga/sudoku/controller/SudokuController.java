package com.ga.sudoku.controller;

import com.ga.sudoku.service.SolveResult;
import com.ga.sudoku.service.SudokuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

// Controller | Exposes solve endpoint. Triggers load -> solve -> save, prints board to console.
@RestController
public class SudokuController {

    private final SudokuService sudokuService;

    public SudokuController(SudokuService sudokuService) {
        this.sudokuService = sudokuService;
    }

    // GET /solve?file=puzzle1 (default puzzle1). Returns JSON with file, solved, solveTimeMs, solveTimeSeconds, message.
    @GetMapping("/solve")
    public ResponseEntity<Map<String, Object>> solve(
            @RequestParam(defaultValue = "puzzle1") String file) {
        SolveResult result = sudokuService.solvePuzzle(file);
        String message = result.solved()
                ? "Puzzle solved in " + result.getSolveTimeSeconds() + " seconds. Check console for board output."
                : "No solution found.";
        return ResponseEntity.ok(Map.of(
                "file", file,
                "solved", result.solved(),
                "solveTimeMs", result.solveTimeMs(),
                "solveTimeSeconds", result.getSolveTimeSeconds(),
                "message", message
        ));
    }
}
