package com.polytech.et4;

import com.polytech.et4.Semaphore;
import com.polytech.et4.Voiture;
import com.polytech.et4.Bicolore;

public class FeuTricolore extends Semaphore {
	
	int couleurFeu = Tricolore.ROUGE;
	
	public FeuTricolore(int sens, SegmentRoute r)
	{
		super(sens, r);
	}
	
	public void changerCouleur(int t) {
		couleurFeu = t;
	}

	@Override
	public float vitesseApproche(Voiture v) {
		if(couleurFeu == Tricolore.ROUGE) 
			return Math.min(Math.abs((v.getPosition() - this.getPosition()+ (m_sens == SensDeplacement.ARRIERRE ? 1 : -1))/v.getVitesseMax()), 1);
		else if(couleurFeu == Tricolore.ORANGE) 
			return 1.0f/2.0f;
		return 1;
	}
	
	public int getCouleur()
	{
		return couleurFeu;
	}
	
	@Override
	public boolean peutPasser(Voiture v) {
		if(v.getPosition() == m_position && v.getSens() == m_sens)
			return couleurFeu != Tricolore.ROUGE;
		return true;
	}
}
