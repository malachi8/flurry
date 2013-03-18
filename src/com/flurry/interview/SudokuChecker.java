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
		System.out.println("grid is valid");

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
		if (rowCnt != N) {
			System.err.println("grid columns not equal to grid rows");
			System.exit(0);
		}

		// make sure grid size has integer square root

		if (Math.sqrt(N) % 1.0 != 0.0) {
			System.err.println("grid columns not equal to grid rows");
			System.exit(0);
		}

		targetSum = (N) * (N + 1) / 2;

	}

	private void validateGrid() {

		// loop through rows

		rowByRowChecker().validate();
		columnByColumnChecker().validate();

	}

	public SukokuChecker rowByRowChecker() {
		return new RowByRowChecker();
	}

	public SukokuChecker columnByColumnChecker() {
		return new ColumnByColumnChecker();
	}

	// an iterator, doesn't implement remove() since it's optional

	private abstract class SukokuChecker {

		protected int i, j;

		protected abstract Integer nextElement();

		public void validate() {

			for (i = 0; i < N; i++) {
				
				int total = 0;
				boolean[] bitBucket = new boolean[N];
				
				for (j = 0; j < N; j++) {

					Integer cellValue = nextElement();

					if (cellValue > N || cellValue <= 0) {
						System.err.println("value is out of range: "
								+ cellValue);
						throw new RuntimeException("value is out of range: "
								+ cellValue);
					}

					if (bitBucket[cellValue - 1]) {
						System.err.println("value is repeated: " + cellValue);
						throw new RuntimeException("value is repeated: "
								+ cellValue);
					}

					bitBucket[cellValue - 1] = true;

					total = total + cellValue;
				}
				
				if (total != targetSum) {
					System.err.println("row sum incorrect sum: " + total
							+ " target sum: " + targetSum);
					throw new RuntimeException("row sum incorrect sum: " + total
							+ " target sum: " + targetSum);
				}
			}

		}
	}

	private class ColumnByColumnChecker extends SukokuChecker {

		protected Integer nextElement() {
			return new Integer(grid[j][i]);
		}

	}

	private class RowByRowChecker extends SukokuChecker {

		protected Integer nextElement() {
			return new Integer(grid[i][j]);
		}

	}

}
