package com.flurry.interview;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
		if(sudokuChecker.check(args[0]))
			System.out.println("grid is valid");
		
	}

	public boolean check(String fileName) {
		try {
			return validateGrid(inputGrid(fileName));			
		} catch (IOException ioe) {
			System.err.println("Could not open " + fileName);
		} catch (NumberFormatException nfe) {
			System.err.println("Invalid integer input");
		} catch (Exception e) {
			System.err.println("Exception occured " + e.getMessage());
		}
		return false;
		// grid is of proper size
		// determine if solution works
		

	}
	
	public boolean validateGrid(List<String> records) {
		try {
			acceptGrid(records);
			validateGrid();
			return true;
		} catch (Exception e) {
			System.err.println("Exception occured " + e.getMessage());
			return false;
		}
	}

	public String readAll() {
		if (!scanner.hasNextLine()) {
			return null;
		}

		// reference:
		// http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html
		return scanner.useDelimiter("\\s+").next();
	}

	private List<String> inputGrid(String fileName) throws Exception {

		File file = new File(fileName);
		if (file.exists()) {
			scanner = new Scanner(file, charsetName);
			scanner.useLocale(usLocale);
		}

		List<String> records = new ArrayList<String>();
		while (scanner.hasNext()) {
			records.add(readAll());
		}

		return records;
	}
	
	private void acceptGrid(List<String> records) throws Exception {
		int rowCnt = 0;
		for (String record : records) {
			String[] fields = record.trim().split(",");
			if (N == 0) {
				N = fields.length;
				grid = new int[N][N];
			} else {
				// make sure each row has same number of columns
				if (fields.length != N) {
					throw new Exception("Invalid row size");
					
				}
			}
			int colCnt = 0;
			for (String field : fields) {
				int iField = Integer.parseInt(field);
				if(iField == 0 || iField > N){
					throw new Exception("Invalid value: " + iField);
				}
				grid[rowCnt][colCnt++] = iField;
			}
			rowCnt++;
		}

		// make sure grid is square
		if (rowCnt != N) {
			throw new Exception("grid columns not equal to grid rows");
			
		}

		// make sure grid size has integer square root

		if (Math.sqrt(N) % 1.0 != 0.0) {
			throw new Exception("grid columns not equal to grid rows");
			
		}

		targetSum = (N) * (N + 1) / 2;
	}

	private void validateGrid() throws Exception {

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

		private void validate() throws Exception{

			for (i = 0; i < N; i++) {
				
				int total = 0;
				boolean[] bitBucket = new boolean[N];
				
				for (j = 0; j < N; j++) {

					Integer cellValue = nextElement();

					if (cellValue > N || cellValue <= 0) {
						throw new Exception("value is out of range: "
								+ cellValue);
						
					}

					if (bitBucket[cellValue - 1]) {
						throw new Exception("value is repeated: " + cellValue);
						
					}

					bitBucket[cellValue - 1] = true;

					total = total + cellValue;
				}
				
				if (total != targetSum) {
					throw new Exception("row sum incorrect sum: " + total
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
