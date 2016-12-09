package com.polytech.et4;

import com.polytech.et4.Semaphore;
import com.polytech.et4.Voiture;
import com.polytech.et4.Bicolore;

public class FeuTricolore extends Semaphore {
	
	Tricolore couleurFeu = Tricolore.ROUGE;
	
	public void changerCouleur(Tricolore t) {
		couleurFeu = t;
	}

	@Override
	public float vitesseApproche(Voiture v) {
		if(couleurFeu == Tricolore.ROUGE) return 0;
		else if(couleurFeu == Tricolore.ORANGE) return 1.0f/2.0f;
		return v.getVitesseCourante();
	}
	
	@Override
	public boolean peutPasser() {
		return couleurFeu != Tricolore.ROUGE;
	}
}