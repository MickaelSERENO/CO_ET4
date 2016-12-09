package com.polytech.et4;
import com.polytech.et4.SensDeplacement;

public class Element 
{
	private int m_position;
	private int m_sens;
	
	Element(int position, int sens)
	{
		m_position = position;
		m_sens = sens;
	}

	void setPosition(int position)
	{
		m_position = position;
	}
	
	int getPosition()
	{
		return m_position;
	}
	
	void setSens(int sens)
	{
		m_sens = sens;
	}
	
	int getSens()
	{
		return m_sens;
	}
}
