package com.polytech.et4;


public class CapteurVitesse extends Capteur {

	private Voiture v_vitesse;
	
	public CapteurVitesse(int position, int sens)
	{
		super(position, sens);
	}
	
	//Recupere la vitesse courante de la voiture
	public int  getVitesse(){	
		return v_vitesse.getVitesseCourante();
	}
}
