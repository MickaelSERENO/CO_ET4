package com.polytech.et4;

import com.polytech.et4.Element;

public abstract class Semaphore extends Element implements Obstacle {
	
	public Semaphore(int sens, SegmentRoute r)
	{
		super((sens == SensDeplacement.ARRIERRE ? r.getPositionDebut() : r.getPositionFin()), sens, r);
		r.setSemaphore(this, sens);
	}
	

	@Override
	public boolean peutPasser(Voiture v)
	{
		return false;
	}
	
	public abstract float vitesseApproche(Voiture v);

}
