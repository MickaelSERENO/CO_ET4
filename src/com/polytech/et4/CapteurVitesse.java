package com.polytech.et4;


public class CapteurVitesse extends Capteur {

	private Voiture v_vitesse;
	
	//Recupere la vitesse courante de la voiture
	public int  getVitesse(){	
		return v_vitesse.getVitesseCourante();
		
	}
}
