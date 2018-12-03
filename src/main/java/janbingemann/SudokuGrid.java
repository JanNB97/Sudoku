package janbingemann;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class SudokuGrid implements Cloneable
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

			List<Field> setFields = new ArrayList<>();
			setFields.add(field);

			do
			{
				setFields = this.refreshAllFields(setFields);
				if(setFields == null)
				{
					return true;
				}
			}
			while (!setFields.isEmpty());
			return true;
		}

		return false;
	}

	public List<Field> refreshAllFields(List<Field> fields)
	{
		List<Field> setFields = new ArrayList<>();
		for(Field field : fields)
		{
			List<Field> newSetFields = this.refreshFields(field);
			if(newSetFields == null)
			{
				return null;
			}
			setFields.addAll(newSetFields);
		}

		return setFields;
	}

	public List<Field> refreshFields(Field field)
	{
		List<Field> setFields = new ArrayList<>();

		if(this.isUnsolvable)
		{
			throw new IllegalArgumentException();
		}

		List<Integer> allPossibleDigits = field.getPossibleDigits();
		if(allPossibleDigits.size() != 1)
		{
			return null;
		}

		int digit = allPossibleDigits.get(0);

		int x = field.getX();
		int y = field.getY();

		List<Field> horizontalSetFields = this.refreshHorizontalVertical(x, y, digit, true);
		if(horizontalSetFields == null)
		{
			return null;
		}
		if(!horizontalSetFields.isEmpty())
		{
			setFields.addAll(horizontalSetFields);
		}

		List<Field> verticalSetFields = this.refreshHorizontalVertical(x, y, digit, false);
		if(verticalSetFields == null)
		{
			return null;
		}
		if(!verticalSetFields.isEmpty())
		{
			setFields.addAll(verticalSetFields);
		}

		List<Field> regionSetFields = this.refreshRegion(field, digit);
		if(regionSetFields == null)
		{
			return null;
		}
		if(!regionSetFields.isEmpty())
		{
			setFields.addAll(regionSetFields);
		}

		return setFields;
	}

	private List<Field> refreshHorizontalVertical(int x, int y, int digit, boolean horizontal)
	{
		List<Field> setFields = new ArrayList<>();

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

			Field setField = this.removePossibleDigit(field, digit);
			if(this.isUnsolvable)
			{
				return null;
			}

			if(setField != null)
			{
				setFields.add(setField);
			}
		}

		return setFields;
	}

	private List<Field> refreshRegion(Field field, int digit)
	{
		List<Field> setFields = new ArrayList<>();

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
				Field setField = this.removePossibleDigit(f, digit);
				if(this.isUnsolvable)
				{
					return null;
				}

				if(setField != null)
				{
					setFields.add(setField);
				}
			}
		}

		return setFields;
	}

	private Field removePossibleDigit(Field field, int digit)
	{
		if(field.removePossibleDigit(digit))
		{
			this.isUnsolvable = field.isUnsolvable();
			if(this.isUnsolvable)
			{
				return null;
			}

			if(field.isSet())
			{
				this.freeFields.remove(field);
				return field;
			}
		}

		return null;
	}

	// --- --- --- Overwritten from object --- --- ---
	@Override
	protected SudokuGrid clone() throws CloneNotSupportedException
	{
		SudokuGrid sudokuGrid = (SudokuGrid)super.clone();

		sudokuGrid.grid = new Field[this.size][this.size];
		for(int i = 0; i < this.size; i++)
		{
			for(int j = 0; j < this.size; j++)
			{
				sudokuGrid.grid[i][j] = this.grid[i][j].clone();
			}
		}

		sudokuGrid.freeFields = new ArrayList<>(this.freeFields);

		return sudokuGrid;
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
