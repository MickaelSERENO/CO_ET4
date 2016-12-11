package com.polytech.et4;
import com.polytech.et4.SensDeplacement;

public class Element 
{
	protected int m_position;
	protected int m_sens;
	protected SegmentRoute m_segmentRoute;
	
	public Element(int position, int sens, SegmentRoute r)
	{
		m_position = position;
		m_sens = sens;
		m_segmentRoute = r;
	}

	public void setPosition(int position)
	{
		m_position = position;
	}
	
	public int getPosition()
	{
		return m_position;
	}
	
	public void setSens(int sens)
	{
		m_sens = sens;
	}
	
	public int getSens()
	{
		return m_sens;
	}
}
