package com.polytech.et4;

import com.polytech.et4.ElemRegulation;

public class CapteurPresence extends Capteur {

	private boolean m_est_present = false;
	private boolean m_estNotifie  = false;
	
	public boolean  getPresence()
	{
		if(m_estNotifie)
		{
			return m_est_present = true;
		}
		else
		{
			return m_est_present = false;
		}		
	}
}
