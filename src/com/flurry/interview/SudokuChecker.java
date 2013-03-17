package com.flurry.interview;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class SudokuChecker {

	private final static String charsetName = "UTF-8";
	private final static Locale usLocale = new Locale("en", "US");

	private Scanner scanner;
	private int N = 0;
	private int[][] grid;
	private int targetSum = 0;

	public static void main(String[] args) {

		SudokuChecker sudokuChecker = new SudokuChecker();
		sudokuChecker.check(args[0]);
	}

	public void check(String fileName) {
		try {
			inputGrid(fileName);

		} catch (IOException ioe) {
			System.err.println("Could not open " + fileName);
		} catch (NumberFormatException nfe) {
			System.err.println("Invalid integer input");
		} catch (Exception e) {
			System.err.println("Exception occured " + e.getMessage());
		}

		// grid is of proper size
		// determine if solution works

		validateGrid();
		System.err.println("grid is valid");

	}

	public String readAll() {
		if (!scanner.hasNextLine()) {
			return null;
		}

		// reference:
		// http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html
		return scanner.useDelimiter("\\s+").next();
	}

	private void inputGrid(String fileName) throws Exception {

		File file = new File(fileName);
		if (file.exists()) {
			scanner = new Scanner(file, charsetName);
			scanner.useLocale(usLocale);
		}

		int rowCnt = 0;
		while (scanner.hasNext()) {
			String[] fields = readAll().trim().split(",");
			if (N == 0) {
				N = fields.length;
				grid = new int[N][N];
			} else {
				// make sure each row has same number of columns
				if (fields.length != N) {
					System.err.println("Invalid row size");
					System.exit(0);
				}
			}
			int colCnt = 0;
			for (String field : fields) {
				grid[rowCnt][colCnt++] = Integer.parseInt(field);
			}
			rowCnt++;
		}

		// make sure grid is square
		try {
			assert (rowCnt == N);
		} catch (AssertionError ae) {
			System.err.println("grid columns not equal to grid rows");
			System.exit(0);
		}

		// make sure grid size has integer square root
		try {
			assert (Math.sqrt(N) % 1.0 == 0.0);
		} catch (AssertionError ae) {
			System.err.println("grid columns not equal to grid rows");
			System.exit(0);
		}

		targetSum = (N) * (N + 1) / 2;

	}

	private void validateGrid() {

		// loop through rows

		for (int i = 0; i < N; i++) {
			boolean[] bitBucket = new boolean[N];
			int rowSum = 0;
			for (int j = 0; j < N; j++) {
				try {
					assert (grid[i][j] <= N && grid[i][j] >= 0);
				} catch (AssertionError ae) {
					System.err.println("value is out of range row: " + i
							+ " col: " + j + " value: " + grid[i][j]);

				}
				try {
					int cellValue = grid[i][j];
					assert (!bitBucket[cellValue - 1]);
					bitBucket[cellValue - 1] = true;
					rowSum = rowSum + grid[i][j];

				} catch (AssertionError ae) {
					System.err.println("value is repeated at row: " + i
							+ " col: " + j + " value: " + grid[i][j]);

				}
			}
			try {
				assert (rowSum == targetSum);
			} catch (AssertionError ae) {
				System.err.println("row sum incorrect row: " + i + " sum: "
						+ rowSum + " target sum: " + targetSum);

			}

		}

		for (int i = 0; i < N; i++) {
			boolean[] bitBucket = new boolean[N];
			int colSum = 0;
			for (int j = 0; j < N; j++) {
				try {
					int cellValue = grid[j][i];
					assert (!bitBucket[cellValue - 1]);
					bitBucket[cellValue - 1] = true;
					colSum = colSum + grid[j][i];

				} catch (AssertionError ae) {
					System.err.println("value is repeated at row: " + j
							+ " col: " + i + " value: " + grid[j][i]);

				}
			}
			try {
				assert (colSum == targetSum);
			} catch (AssertionError ae) {
				System.err.println("col sum incorrect col: " + i + " sum: "
						+ colSum + " target sum: " + targetSum);

			}

		}
	}

}
