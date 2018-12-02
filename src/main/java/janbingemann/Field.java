package janbingemann;

import java.util.HashSet;
import java.util.Set;

public class Field
{
	private Set<Integer> possibleDigits = new HashSet<>();

	private final int x;
	private final int y;

	private final int startRegionX;
	private final int endRegionX;
	private final int startRegionY;
	private final int endRegionY;

	public Field(SudokuGrid sudokuGrid, int x, int y)
	{
		this.x = x;
		this.y = y;

		for(int i = 0; i < sudokuGrid.size(); i++)
		{
			this.possibleDigits.add(i);
		}

		this.startRegionX = (x / sudokuGrid.getRegionWidth()) * sudokuGrid.getRegionWidth();
		this.endRegionX = this.startRegionX + sudokuGrid.getRegionWidth();
		this.startRegionY = (y / sudokuGrid.getRegionHeight()) * sudokuGrid.getRegionHeight();
		this.endRegionY = this.startRegionY + sudokuGrid.getRegionHeight();
	}

	public boolean isPossibleDigit(int digit)
	{
		return this.possibleDigits.contains(digit);
	}

	public boolean removePossibleDigit(int digit)
	{
		return this.possibleDigits.remove(digit);
	}

	public Set<Integer> getPossibleDigits()
	{
		return this.possibleDigits;
	}

	public void setDigit(int digit)
	{
		this.possibleDigits.clear();
		this.possibleDigits.add(digit);
	}

	public boolean isUnsolvable()
	{
		return this.possibleDigits.isEmpty();
	}

	public int getStartRegionX()
	{
		return startRegionX;
	}

	public int getEndRegionX()
	{
		return endRegionX;
	}

	public int getStartRegionY()
	{
		return startRegionY;
	}

	public int getEndRegionY()
	{
		return endRegionY;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public boolean isSet()
	{
		return this.possibleDigits.size() == 1;
	}

	@Override
	public String toString()
	{
		if(this.isSet())
		{
			return this.possibleDigits.iterator().next().toString();
		}
		else
		{
			return " ";
		}
	}
}
