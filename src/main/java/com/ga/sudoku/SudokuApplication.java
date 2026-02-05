package com.ga.sudoku;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SudokuApplication {

	public static void main(String[] args) {
		SpringApplication.run(SudokuApplication.class, args);

		System.out.println(">> Sudoku Application Started <<");
		System.out.println("Usage: GET http://localhost:8080/solve             (default: puzzle1)");
		System.out.println("       GET http://localhost:8080/solve?file=puzzle1");
		System.out.println("       GET http://localhost:8080/solve?file=puzzle2");
		System.out.println("       GET http://localhost:8080/solve?file=puzzle3");
		System.out.println("       GET http://localhost:8080/solve?file=puzzle4");
		System.out.println("Board output is printed in this console.");
	}

}
