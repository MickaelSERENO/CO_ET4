package com.polytech.et4;

import com.polytech.et4.Semaphore;
import com.polytech.et4.Voiture;
import com.polytech.et4.Bicolore;

public class FeuTricolore extends Semaphore {
	
	int couleurFeu = Tricolore.ROUGE;
	
	public FeuTricolore(int position, int sens)
	{
		super(position, sens);
	}
	
	public void changerCouleur(int t) {
		couleurFeu = t;
	}

	@Override
	public float vitesseApproche(Voiture v) {
		if(couleurFeu == Tricolore.ROUGE) 
			return Math.min(Math.abs((v.getPosition() - this.getPosition())/v.getVitesseMax()), v.getVitesseMax());
		else if(couleurFeu == Tricolore.ORANGE) 
			return 1.0f/2.0f;
		return v.getVitesseCourante();
	}
	
	public int getCouleur()
	{
		return couleurFeu;
	}
	
	@Override
	public boolean peutPasser(Voiture v) {
		return couleurFeu != Tricolore.ROUGE;
	}
}