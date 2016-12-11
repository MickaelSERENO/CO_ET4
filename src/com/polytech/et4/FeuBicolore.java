package com.polytech.et4;
import com.polytech.et4.Semaphore;
import com.polytech.et4.Voiture;
import com.polytech.et4.Bicolore;

public class FeuBicolore extends Semaphore {
	
	int couleurFeu = Bicolore.ROUGE;
	
	FeuBicolore(int sens, SegmentRoute r)
	{
		super(sens, r);
	}
	
	public void changerCouleur(int b) {
		couleurFeu = b;
	}

	@Override
	public float vitesseApproche(Voiture v) {
		if(couleurFeu == Bicolore.ROUGE) 
			return Math.min(Math.abs((v.getPosition() - this.getPosition()+ (m_sens == SensDeplacement.ARRIERRE ? 1 : -1))/v.getVitesseMax()), 1);
		return 1;
	}
	
	public int getCouleur()
	{
		return couleurFeu;
	}
	
	@Override
	public boolean peutPasser(Voiture v) {
		if(v.getPosition() == m_position && v.getSens() == m_sens)
			return couleurFeu != Bicolore.ROUGE;
		return true;
	}
}
