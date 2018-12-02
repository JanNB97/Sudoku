package janbingemann;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SudokuGrid
{
	private final int size;
	private final int regionWidth;
	private final int regionHeight;
	private Field[][] grid;

	private boolean isUnsolvable;
	private List<Field> freeFields = new ArrayList<>();

	public SudokuGrid(int size, int regionWidth, int regionHeight)
	{
		this.size = size;
		this.regionWidth = regionWidth;
		this.regionHeight = regionHeight;

		this.grid = new Field[size][size];

		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				Field newField = new Field(this, j, i);
				this.grid[i][j] = newField;
				this.freeFields.add(newField);
			}
		}
	}

	public boolean setField(int x, int y, int digit)
	{
		if(this.isUnsolvable)
		{
			throw new IllegalArgumentException();
		}

		Field field = grid[y][x];

		if(field.isPossibleDigit(digit))
		{
			field.setDigit(digit);
			this.freeFields.remove(field);
			this.refreshFields(field);
			return true;
		}

		return false;
	}

	public boolean refreshFields(Field field)
	{
		if(this.isUnsolvable)
		{
			throw new IllegalArgumentException();
		}

		Set<Integer> allPossibleDigits = field.getPossibleDigits();
		if(allPossibleDigits.size() != 1)
		{
			return false;
		}
		int digit = allPossibleDigits.iterator().next();

		int x = field.getX();
		int y = field.getY();

		if(this.refreshHorizontalVertical(x, y, digit, true))
		{
			return true;
		}

		if(this.refreshHorizontalVertical(x, y, digit, false))
		{
			return true;
		}

		// regions
		for(int i = field.getStartRegionY(); i < field.getEndRegionY(); i++)
		{
			for(int j = field.getStartRegionX(); j < field.getEndRegionX(); j++)
			{
				if(i == y && j == x)
				{
					continue;
				}

				Field f = this.grid[i][j];
				if(this.removePossibleDigit(f, digit))
				{
					return true;
				}
			}
		}

		return true;
	}

	private boolean refreshHorizontalVertical(int x, int y, int digit, boolean horizontal)
	{
		for(int i = 0; i < size; i++)
		{
			if(i == y && !horizontal || i == x && horizontal)
			{
				continue;
			}

			Field field;
			if(horizontal)
			{
				field = this.grid[y][i];
			}
			else
			{
				field = this.grid[i][x];
			}

			if(this.removePossibleDigit(field, digit))
			{
				return true;
			}
		}

		return false;
	}

	private boolean removePossibleDigit(Field field, int digit)
	{
		if(field.removePossibleDigit(digit))
		{
			this.isUnsolvable = field.isUnsolvable();
			if(this.isUnsolvable)
			{
				return true;
			}

			if(field.isSet())
			{
				this.freeFields.remove(field);
			}
		}

		return false;
	}

	public int size()
	{
		return this.size;
	}

	public int getRegionWidth()
	{
		return regionWidth;
	}

	public int getRegionHeight()
	{
		return regionHeight;
	}

	public boolean isUnsolvable()
	{
		return this.isUnsolvable;
	}

	public boolean isSolved()
	{
		return !this.isUnsolvable && this.freeFields.isEmpty();
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();

		builder.append("--");

		for(int i = 0; i < this.size; i++)
		{
			builder.append(i);
			builder.append("-");
		}
		builder.append("-");
		builder.append(System.lineSeparator());

		for(int i = 0; i < this.size; i++)
		{
			builder.append(i);
			builder.append("|");

			for(int j = 0; j < this.size; j++)
			{
				builder.append(this.grid[i][j].toString());
				builder.append(" ");
			}

			builder.append("|");
			builder.append(System.lineSeparator());
		}

		builder.append("---");
		for(int i = 0; i < this.size; i++)
		{
			builder.append("--");
		}

		return builder.toString();
	}

	public List<Field> getFreeFields()
	{
		return freeFields;
	}
}
