package com.polytech.et4;

import com.polytech.et4.Semaphore;
import com.polytech.et4.Voiture;

public class Stop extends Semaphore {

	public Stop(int position, int sens)
	{
		super(position, sens);
	}
	
	@Override
	public float vitesseApproche(Voiture v) 
	{
		return 0;
	}

}
