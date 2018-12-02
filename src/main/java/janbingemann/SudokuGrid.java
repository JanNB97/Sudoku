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
			while (this.refreshFields(field) == Situation.SOLVABLE_AND_FIELD_IS_SET);
			return true;
		}

		return false;
	}

	public Situation refreshFields(Field field)
	{
		Situation situation = Situation.SOLVABLE_NO_CHANGE;

		if(this.isUnsolvable)
		{
			throw new IllegalArgumentException();
		}

		Set<Integer> allPossibleDigits = field.getPossibleDigits();
		if(allPossibleDigits.size() != 1)
		{
			return null;
		}

		int digit = allPossibleDigits.iterator().next();

		int x = field.getX();
		int y = field.getY();

		switch (this.refreshHorizontalVertical(x, y, digit, true))
		{
			case UNSOLVABLE:
				return Situation.UNSOLVABLE;
			case SOLVABLE_AND_FIELD_IS_SET:
				situation = Situation.SOLVABLE_AND_FIELD_IS_SET;
				break;
		}

		switch (this.refreshHorizontalVertical(x, y, digit, false))
		{
			case UNSOLVABLE:
				return Situation.UNSOLVABLE;
			case SOLVABLE_AND_FIELD_IS_SET:
				situation = Situation.SOLVABLE_AND_FIELD_IS_SET;
				break;
		}

		switch (this.refreshRegion(field, digit))
		{
			case UNSOLVABLE:
				return Situation.UNSOLVABLE;
			case SOLVABLE_AND_FIELD_IS_SET:
				situation = Situation.SOLVABLE_AND_FIELD_IS_SET;
				break;
		}

		return situation;
	}

	private Situation refreshHorizontalVertical(int x, int y, int digit, boolean horizontal)
	{
		Situation situation = Situation.SOLVABLE_NO_CHANGE;

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

			switch (this.removePossibleDigit(field, digit))
			{
				case UNSOLVABLE:
					return Situation.UNSOLVABLE;
				case SOLVABLE_AND_FIELD_IS_SET:
					situation = Situation.SOLVABLE_AND_FIELD_IS_SET;
					break;
			}
		}

		return situation;
	}

	private Situation refreshRegion(Field field, int digit)
	{
		Situation situation = Situation.SOLVABLE_NO_CHANGE;

		int x = field.getX();
		int y = field.getY();

		for(int i = field.getStartRegionY(); i < field.getEndRegionY(); i++)
		{
			for(int j = field.getStartRegionX(); j < field.getEndRegionX(); j++)
			{
				if(i == y && j == x)
				{
					continue;
				}

				Field f = this.grid[i][j];
				switch (this.removePossibleDigit(f, digit))
				{
					case UNSOLVABLE:
						return Situation.UNSOLVABLE;
					case SOLVABLE_AND_FIELD_IS_SET:
						situation = Situation.SOLVABLE_AND_FIELD_IS_SET;
						break;
				}
			}
		}

		return situation;
	}

	private Situation removePossibleDigit(Field field, int digit)
	{
		if(field.removePossibleDigit(digit))
		{
			this.isUnsolvable = field.isUnsolvable();
			if(this.isUnsolvable)
			{
				return Situation.UNSOLVABLE;
			}

			if(field.isSet())
			{
				this.freeFields.remove(field);
				return Situation.SOLVABLE_AND_FIELD_IS_SET;
			}
		}

		return Situation.SOLVABLE_NO_CHANGE;
	}

	// --- --- --- Overwritten from object --- --- ---
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

	// --- --- --- Getter and setter --- --- ---
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

	public List<Field> getFreeFields()
	{
		return freeFields;
	}
}
