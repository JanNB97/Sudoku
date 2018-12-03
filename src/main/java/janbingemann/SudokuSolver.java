package janbingemann;

import java.util.List;

public class SudokuSolver
{
	public static SudokuGrid solve(SudokuGrid inputSudoku) throws CloneNotSupportedException
	{
		SudokuGrid sudokuGrid = inputSudoku.clone();

		List<Field> freeFields = sudokuGrid.getFreeFields();

		for(Field field : freeFields)
		{
			SudokuGrid result = tryField(sudokuGrid, field);
			if (result != null)
			{
				return result;
			}
		}

		return null;
	}

	private static SudokuGrid tryField(SudokuGrid sudoku, Field field) throws CloneNotSupportedException
	{
		for(int digit : field.getPossibleDigits())
		{
			SudokuGrid result = tryDigit(sudoku, field, digit);
			if(result != null)
			{
				return result;
			}
		}

		return null;
	}

	private static SudokuGrid tryDigit(SudokuGrid sudokuGrid, Field field, int digit) throws CloneNotSupportedException
	{
		SudokuGrid newSudoku;
		try {
			newSudoku = sudokuGrid.clone();
		}
		catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}

		newSudoku.setField(field.getX(), field.getY(), digit);

		if(newSudoku.isUnsolvable())
		{
			return null;
		}

		if(newSudoku.isSolved())
		{
			return newSudoku;
		}

		return solve(newSudoku);
	}
}
