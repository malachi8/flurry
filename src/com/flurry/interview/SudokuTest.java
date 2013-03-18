package com.flurry.interview;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class SudokuTest {

	@Test
	public void test1() {

		SudokuChecker sudokuChecker = new SudokuChecker();
		List<String> records = new ArrayList<String>();
		records.add("1,4,2,3");
		records.add("2,3,1,4");
		records.add("4,2,3,1");
		records.add("3,1,4,2");
		sudokuChecker.validateGrid(records);
	}
	
	@Test
	public void test2() {

		SudokuChecker sudokuChecker = new SudokuChecker();
		List<String> records = new ArrayList<String>();
		records.add("1,4,2,3");
		records.add("2,3,1,4");
		records.add("4,2,3,1");
		
		sudokuChecker.validateGrid(records);
	}
	
	@Test
	public void test3() {

		SudokuChecker sudokuChecker = new SudokuChecker();
		List<String> records = new ArrayList<String>();
		records.add("1,4,2,3");
		records.add("2,3,1,4");
		records.add("4,2,3,1");
		records.add("3,1,4,2");
		sudokuChecker.validateGrid(records);
	}
	
	@Test
	public void test4() {

		SudokuChecker sudokuChecker = new SudokuChecker();
		List<String> records = new ArrayList<String>();
		records.add("1,4,2,3");
		records.add("2,3,1,4");
		records.add("4,2,3,1");
		records.add("3,2,4,2");
		sudokuChecker.validateGrid(records);
	}
	
	@Test
	public void test5() {

		SudokuChecker sudokuChecker = new SudokuChecker();
		List<String> records = new ArrayList<String>();
		records.add("1,4,2,3");
		records.add("2,3,1,4");
		records.add("4,2,3,1");
		records.add("3,1,4,0");
		sudokuChecker.validateGrid(records);
	}


}
