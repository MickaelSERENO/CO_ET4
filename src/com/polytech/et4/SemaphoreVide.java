package com.polytech.et4;

public class SemaphoreVide extends Semaphore {

	public SemaphoreVide(int sens, SegmentRoute r)
	{
		super(sens, r);
	}

	//On ramène au pire la voiture sur le sémaphore
	@Override
	public float vitesseApproche(Voiture v) {
		return 1.0f;
	}
	
	public boolean peutPasser(Voiture v)
	{
		return true;
	}
}
