package com.polytech.et4;
import com.polytech.et4.Semaphore;
import com.polytech.et4.Voiture;
import com.polytech.et4.Bicolore;

public class FeuBicolore extends Semaphore {
	
	int couleurFeu = Bicolore.ROUGE;
	
	FeuBicolore(int position, int sens)
	{
		super(position, sens);
	}
	
	public void changerCouleur(int b) {
		couleurFeu = b;
	}

	@Override
	public float vitesseApproche(Voiture v) {
		if(couleurFeu == Bicolore.ROUGE) 
			return Math.min(Math.abs((v.getPosition() - this.getPosition())/v.getVitesseMax()), v.getVitesseMax());
		return 1;
	}
	
	public int getCouleur()
	{
		return couleurFeu;
	}
	
	@Override
	public boolean peutPasser(Voiture v) {
		return couleurFeu != Bicolore.ROUGE;
	}
	

}
