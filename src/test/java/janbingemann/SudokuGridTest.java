package janbingemann;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class SudokuGridTest
{
	private static final int SIZE = 9;
	private static final int REGION_WIDTH = 3;
	private static final int REGION_HEIGHT = 3;

	@Test
	public void testSudokuGrid1()
	{
		SudokuGrid sudokuGrid = new SudokuGrid(SIZE, REGION_WIDTH, REGION_HEIGHT);

		int y;

		y = 0;
		this.setFieldSuccessfully(sudokuGrid, 1, y, 1);
		this.setFieldSuccessfully(sudokuGrid, 2, y, 7);
		this.setFieldSuccessfully(sudokuGrid, 6, y, 4);

		y = 1;
		this.setFieldSuccessfully(sudokuGrid, 3, y, 2);
		this.setFieldSuccessfully(sudokuGrid, 6, y, 8);
		this.setFieldSuccessfully(sudokuGrid, 8, y, 6);

		y = 2;
		this.setFieldSuccessfully(sudokuGrid, 3, y, 4);
		this.setFieldSuccessfully(sudokuGrid, 5, y, 7);
		this.setFieldSuccessfully(sudokuGrid, 8, y, 1);

		y = 3;
		this.setFieldSuccessfully(sudokuGrid, 0, y, 3);
		this.setFieldSuccessfully(sudokuGrid, 1, y, 6);
		this.setFieldSuccessfully(sudokuGrid, 2, y, 5);
		this.setFieldSuccessfully(sudokuGrid, 7, y, 4);
		this.setFieldSuccessfully(sudokuGrid, 8, y, 9);

		y = 4;
		this.setFieldSuccessfully(sudokuGrid, 0, y, 9);
		this.setFieldSuccessfully(sudokuGrid, 2, y, 8);
		this.setFieldSuccessfully(sudokuGrid, 4, y, 7);

		y = 5;
		this.setFieldSuccessfully(sudokuGrid, 0, y, 7);
		this.setFieldSuccessfully(sudokuGrid, 3, y, 3);
		this.setFieldSuccessfully(sudokuGrid, 4, y, 5);
		this.setFieldSuccessfully(sudokuGrid, 6, y, 6);

		y = 6;
		this.setFieldSuccessfully(sudokuGrid, 0, y, 2);
		this.setFieldSuccessfully(sudokuGrid, 1, y, 7);
		this.setFieldSuccessfully(sudokuGrid, 4, y, 6);
		this.setFieldSuccessfully(sudokuGrid, 5, y, 8);
		this.setFieldSuccessfully(sudokuGrid, 6, y, 5);

		y = 7;
		this.setFieldSuccessfully(sudokuGrid, 2, y, 6);
		this.setFieldSuccessfully(sudokuGrid, 5, y, 3);
		this.setFieldSuccessfully(sudokuGrid, 7, y, 9);

		y = 8;
		this.setFieldSuccessfully(sudokuGrid, 0, y, 8);
		this.setFieldSuccessfully(sudokuGrid, 1, y, 3);
		this.setFieldSuccessfully(sudokuGrid, 2, y, 4);
		this.setFieldSuccessfully(sudokuGrid, 5, y, 2);

		System.out.println(sudokuGrid.toString());
	}

	@Test
	public void testSudokuGrid2()
	{
		SudokuGrid sudokuGrid = new SudokuGrid(SIZE, REGION_WIDTH, REGION_HEIGHT);

		int y;

		y = 0;
		this.setFieldSuccessfully(sudokuGrid, 0, y, 4);
		this.setFieldSuccessfully(sudokuGrid, 1, y, 1);
		this.setFieldSuccessfully(sudokuGrid,4, y, 6);
		this.setFieldSuccessfully(sudokuGrid,5, y, 5);
		this.setFieldSuccessfully(sudokuGrid,8, y, 7);

		y = 1;
		this.setFieldSuccessfully(sudokuGrid, 2, y, 6);
		this.setFieldSuccessfully(sudokuGrid, 5, y, 7);
		this.setFieldSuccessfully(sudokuGrid, 6, y, 4);
		this.setFieldSuccessfully(sudokuGrid, 7, y, 8);

		y = 2;
		this.setFieldSuccessfully(sudokuGrid, 0, y, 2);
		this.setFieldSuccessfully(sudokuGrid, 2, y, 7);
		this.setFieldSuccessfully(sudokuGrid, 3, y, 4);
		this.setFieldSuccessfully(sudokuGrid, 4, y, 9);
		this.setFieldSuccessfully(sudokuGrid, 8, y, 6);

		y = 3;
		this.setFieldSuccessfully(sudokuGrid, 1, y, 6);
		this.setFieldSuccessfully(sudokuGrid, 4, y, 7);
		this.setFieldSuccessfully(sudokuGrid, 6, y, 1);

		y = 4;
		this.setFieldSuccessfully(sudokuGrid, 0, y, 3);
		this.setFieldSuccessfully(sudokuGrid, 2, y, 1);
		this.setFieldSuccessfully(sudokuGrid, 3, y, 5);
		this.setFieldSuccessfully(sudokuGrid, 7, y, 7);
		this.setFieldSuccessfully(sudokuGrid, 8, y, 2);

		y = 5;
		this.setFieldSuccessfully(sudokuGrid, 1, y, 9);
		this.setFieldSuccessfully(sudokuGrid, 4, y, 4);
		this.setFieldSuccessfully(sudokuGrid, 5, y, 2);
		this.setFieldSuccessfully(sudokuGrid, 6, y, 3);
		this.setFieldSuccessfully(sudokuGrid, 8, y, 8);

		y = 6;
		this.setFieldSuccessfully(sudokuGrid, 0, y, 1);
		this.setFieldSuccessfully(sudokuGrid, 2, y, 8);
		this.setFieldSuccessfully(sudokuGrid, 3, y, 6);
		this.setFieldSuccessfully(sudokuGrid, 7, y, 2);
		this.setFieldSuccessfully(sudokuGrid, 8, y, 9);

		y = 7;
		this.setFieldSuccessfully(sudokuGrid, 1, y, 2);
		this.setFieldSuccessfully(sudokuGrid, 4, y, 1);
		this.setFieldSuccessfully(sudokuGrid, 5, y, 8);
		this.setFieldSuccessfully(sudokuGrid, 6, y, 6);
		this.setFieldSuccessfully(sudokuGrid, 7, y, 4);

		y = 8;
		this.setFieldSuccessfully(sudokuGrid, 0, y, 6);
		this.setFieldSuccessfully(sudokuGrid, 3, y, 3);
		this.setFieldSuccessfully(sudokuGrid, 7, y, 1);

		System.out.println(sudokuGrid.toString());
	}

	@Test
	public void testSudokuGrid3()
	{
		SudokuGrid sudokuGrid = new SudokuGrid(6, 3, 2);
		int y = 0;
		this.setFieldSuccessfully(sudokuGrid, 0, y, 2);
		this.setFieldSuccessfully(sudokuGrid, 2, y, 4);
		this.setFieldSuccessfully(sudokuGrid, 4, y, 3);

		y = 1;
		this.setFieldSuccessfully(sudokuGrid, 1, y, 6);
		this.setFieldSuccessfully(sudokuGrid, 5, y, 1);

		y = 2;
		this.setFieldSuccessfully(sudokuGrid, 0, y, 6);
		this.setFieldSuccessfully(sudokuGrid, 2, y, 1);
		this.setFieldSuccessfully(sudokuGrid, 3, y, 3);

		y = 3;
		this.setFieldSuccessfully(sudokuGrid, 1, y, 4);
		this.setFieldSuccessfully(sudokuGrid, 5, y, 6);

		y = 4;
		this.setFieldSuccessfully(sudokuGrid, 2, y, 2);
		this.setFieldSuccessfully(sudokuGrid, 4, y, 6);
		this.setFieldSuccessfully(sudokuGrid, 5, y, 3);

		y = 5;
		this.setFieldSuccessfully(sudokuGrid, 1, y, 3);
		this.setFieldSuccessfully(sudokuGrid, 3, y, 5);

		SudokuGrid solvedSudoku = null;
		try {
			solvedSudoku = SudokuSolver.solve(sudokuGrid);
		}
		catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}

		System.out.println(sudokuGrid.toString() + "\n");
		System.out.println(solvedSudoku);
	}

	private void setFieldSuccessfully(SudokuGrid sudokuGrid, int x, int y, int digit)
	{
		Assert.assertThat(sudokuGrid.setField(x, y, digit), is(true));
		Assert.assertThat(sudokuGrid.isUnsolvable(), is(false));
	}
}
