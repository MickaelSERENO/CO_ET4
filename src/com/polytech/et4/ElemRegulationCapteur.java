package com.polytech.et4;

import com.polytech.et4.ElemRegulation;

public abstract class ElemRegulationCapteur extends ElemRegulation
{
	Capteur m_capt;

	ElemRegulationCapteur(Capteur c)
	{
		m_capt = c;
		m_capt.setElemRegulation(this);
	}

	/* \brief Permet de gérer les évènements*/
	public abstract void notifie();
}
