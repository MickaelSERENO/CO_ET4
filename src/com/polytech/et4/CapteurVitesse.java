package com.polytech.et4;


public class CapteurVitesse extends Capteur {
	public CapteurVitesse(int position, int sens, SegmentRoute r)
	{
		super(position, sens, r);
	}
	
	//Recupere la vitesse courante de la voiture
	public int getVitesse(){
		if(m_voitureCourante != null)
			return m_voitureCourante.getVitesseCourante();
		return 0;
	}
}
