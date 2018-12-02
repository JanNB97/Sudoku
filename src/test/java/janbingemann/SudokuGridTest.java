package janbingemann;

import org.junit.Test;

public class SudokuGridTest
{
	private static final int SIZE = 9;
	private static final int REGION_WIDTH = 3;
	private static final int REGION_HEIGHT = 3;

	@Test
	public void testSudokuGrid()
	{
		SudokuGrid sudokuGrid = new SudokuGrid(SIZE, REGION_WIDTH, REGION_HEIGHT);

		sudokuGrid.setField(3, 3, 5);
		System.out.println(sudokuGrid);

		sudokuGrid.setField(0, 0, 1);
		System.out.println(sudokuGrid);

		System.out.println(sudokuGrid.setField(4, 4, 1));
		System.out.println(sudokuGrid.setField(8, 8, 5));
		System.out.println(sudokuGrid);

		System.out.println(sudokuGrid.setField(5, 1, 5));
		System.out.println(sudokuGrid);

		System.out.println(sudokuGrid.setField(5, 1, 5));
		System.out.println(sudokuGrid);

		System.out.println(sudokuGrid.setField(0, 2, 5));
		System.out.println(sudokuGrid);

		System.out.println(sudokuGrid.setField(1, 7, 5));
		System.out.println(sudokuGrid);

		System.out.println(sudokuGrid.setField(2, 6, 5));
		System.out.println(sudokuGrid);

		System.out.println(sudokuGrid.setField(7, 0, 5));
		System.out.println(sudokuGrid);

		System.out.println(sudokuGrid.setField(2, 5, 5));
		System.out.println(sudokuGrid);

		System.out.println(sudokuGrid.setField(6, 4, 5));
		System.out.println(sudokuGrid);
	}
}
