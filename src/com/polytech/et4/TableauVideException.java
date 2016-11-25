package com.polytech.et4;

public class TableauVideException extends Exception
{
	public TableauVideException()
	{
		super("Le tableau passé en paramètre est vide");
	}
}
