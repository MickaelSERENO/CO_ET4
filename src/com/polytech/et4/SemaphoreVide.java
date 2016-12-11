package com.polytech.et4;

public class SemaphoreVide extends Semaphore {

	public SemaphoreVide(int sens, SegmentRoute r)
	{
		super(sens, r);
	}

	//On ramène au pire la voiture sur le sémaphore
	@Override
	public float vitesseApproche(Voiture v) {
		//Si on veut que la voiture s'arrête sur ce sémaphore avant de passer à la route suivante
		return Math.min(Math.abs((v.getPosition() - this.getPosition())/v.getVitesseMax()), 1);
		//Si on veut que la voiture continue 
		//return 1;
	}
	
	public boolean peutPasser(Voiture v)
	{
		return true;
	}
}