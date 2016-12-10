package com.polytech.et4;

import com.polytech.et4.Element;

public abstract class Semaphore extends Element implements Obstacle {
	
	private ElemRegulation er;
	
	public Semaphore(int position, int sens)
	{
		super(position, sens);
	}
	

	@Override
	public boolean peutPasser(Voiture v)
	{
		return false;
	}
	
	public abstract float vitesseApproche(Voiture v);

}
