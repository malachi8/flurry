package com.flurry.interview;



import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SudokuTest {

	@Test
	public void correctGrid() {

		SudokuChecker sudokuChecker = new SudokuChecker();
		List<String> records = new ArrayList<String>();
		records.add("1,4,2,3");
		records.add("2,3,1,4");
		records.add("4,2,3,1");
		records.add("3,1,4,2");
		assert(sudokuChecker.validateGrid(records));
	}
	
	@Test
	public void tooFewRows() {

		SudokuChecker sudokuChecker = new SudokuChecker();
		List<String> records = new ArrayList<String>();
		records.add("1,4,2,3");
		records.add("2,3,1,4");
		records.add("4,2,3,1");
		
		assert(!sudokuChecker.validateGrid(records));
	}
	
	@Test
	public void columnError() {

		SudokuChecker sudokuChecker = new SudokuChecker();
		List<String> records = new ArrayList<String>();
		records.add("1,4,2,3");
		records.add("2,3,1,");
		records.add("4,2,3,1");
		records.add("3,1,4,2");
		assert(!sudokuChecker.validateGrid(records));
	}
	
	@Test
	public void incorrectValue() {

		SudokuChecker sudokuChecker = new SudokuChecker();
		List<String> records = new ArrayList<String>();
		records.add("1,4,2,3");
		records.add("2,3,1,4");
		records.add("4,2,3,1");
		records.add("3,2,4,2");
		assert(!sudokuChecker.validateGrid(records));
	}
	
	@Test
	public void testZero() {

		SudokuChecker sudokuChecker = new SudokuChecker();
		List<String> records = new ArrayList<String>();
		records.add("1,4,2,3");
		records.add("2,3,1,4");
		records.add("4,2,3,1");
		records.add("3,1,4,0");
		assert(!sudokuChecker.validateGrid(records));
	}


}
