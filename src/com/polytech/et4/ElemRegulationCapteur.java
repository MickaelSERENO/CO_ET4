package com.polytech.et4;

import com.polytech.et4.ElemRegulation;

class ElemRegulationCapteur extends ElemRegulation
{
	Capteur m_capt;

	ElemRegulationCapteur(Capteur c)
	{
		m_capt = c;
	}

	/* \brief Permet de gérer les évènements*/
	public abstract void etreNotifie();
}
