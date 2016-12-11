package com.polytech.et4;

import com.polytech.et4.ElemRegulation;

public class CapteurPresence extends Capteur {

	private boolean m_est_present = false;

	public CapteurPresence(int position, int sens, SegmentRoute r)
	{
		super(position, sens, r);
	}
	
	public boolean  getPresence()
	{
		if(m_voitureCourante != null)
			return m_voitureCourante.getPosition() == m_position && m_voitureCourante.getSens() == m_sens;
		return false;
	}
}
