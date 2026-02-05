# Sudoku Spring Boot - Mini Project

A Spring Boot application that loads Sudoku puzzles from files, solves them and saves the solution. The board is displayed in ASCII format in the console.

## Features

- Load a 9×9 Sudoku puzzle from a text file (e.g. `puzzle1.txt`)
- Solve the puzzle
- Save the solution to a file (e.g. `puzzle1_solution.txt`)
- Print the current board to the console in a readable format
- REST endpoint to trigger solve: `GET /solve?file=puzzle1`
- **Bonus:** Input validation (values 0–9 only; fixed cells cannot be overwritten)
- **Bonus:** Timer showing how long the solve took (in API response and console)
- **Bonus:** JSON error responses for missing file (404) and invalid file content (400)

## Technical Details

- **IDE:** IntelliJ IDEA Community 2025.3.2
- **JDK:** 17.0.17
- **Spring Boot:** 4.0.2 (Maven-based)

## How to Run

1. Open the project in IntelliJ IDEA Community 2025.3.2
2. Ensure the working directory is the project root (where `puzzle1.txt` and `pom.xml` are).
3. Set the Configuration to use JDK 17.0.17
4. Run the application 
5. The app starts on **http://localhost:8080**

## How to Use

Usage (same as at **http://localhost:8080/** and in the console on startup):

- **Usage page:** http://localhost:8080/
- **Solve default (puzzle1):** http://localhost:8080/solve
- **Solve a specific puzzle:**
  - http://localhost:8080/solve?file=puzzle1
  - http://localhost:8080/solve?file=puzzle2
  - http://localhost:8080/solve?file=puzzle3
  - http://localhost:8080/solve?file=puzzle4

The corresponding `.txt` file must exist in the project root. The response is JSON and includes `solved`, `solveTimeMs`, `solveTimeSeconds`, and `message`. The unsolved and solved boards are printed in the **console** in ASCII format.

## Puzzle File Format

- **Input:** A `.txt` file with 9 lines. Each line has 9 space-separated digits (0–9). `0` means an empty cell; `1`–`9` are given clues.
- **Output:** The solution is saved as `<basename>_solution.txt` in the same directory (e.g. `puzzle1.txt` → `puzzle1_solution.txt`).



## Project Structure

```
com.ga.sudoku
├── controller     -> SudokuController, GlobalExceptionHandler
├── service        -> SudokuService, SolveResult
├── model          -> Sudoku, SudokuBoard, SudokuCell, SudokuSolver
└── exception      -> InvalidCharacterException, SudokuFileNotFoundException
```

- **Sudoku** - Loads from file, saves solution, prints board (uses SudokuBoard).
- **SudokuBoard** - 9×9 grid with get/set and validation (row, column, 3×3 box).
- **SudokuCell** - Single cell (row, col, value, fixed).
- **SudokuSolver** - Solver for the 9×9 grid.

## Included Puzzle Files

The project includes `puzzle1.txt` through `puzzle4.txt` in the project root. After solving, the corresponding `puzzleN_solution.txt` files are generated in the same folder.

## Errors

- **Missing file:** Requesting a non-existent puzzle (e.g. `?file=nosuchfile`) returns **404** with a JSON body: `error`, `message`, `status`.
- **Invalid file content:** If the puzzle file contains any character that is not a digit 0–9 (or allowed whitespace), the API returns **400** with a JSON error body.

No database is used; all puzzle data is read from and written to files.

---

## Reflection

**Challenges:** File path so the app finds `puzzle1.txt` was tricky. So was splitting logic between Sudoku, SudokuBoard and SudokuSolver.

**Efficiency:** It's fast for 9×9 (milliseconds). For harder puzzles we could pick the cell with fewest options first.

**Applications (stack):** Recursion uses the call stack (push when we call, pop when we return). Same idea: undo/redo, matching brackets. When you need to go back or reverse order, use a stack.
