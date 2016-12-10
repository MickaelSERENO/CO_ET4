package com.polytech.et4;

import com.polytech.et4.ElemRegulation;

public class CapteurPresence extends Capteur {

	private boolean m_est_present = false;
	
	public CapteurPresence(int position, int sens)
	{
		super(position, sens);
	}
	
	public boolean  getPresence()
	{
		return m_est_present;
	}
}
