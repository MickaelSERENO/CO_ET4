package com.polytech.et4;

import com.polytech.et4.Semaphore;
import com.polytech.et4.Voiture;

public class Stop extends Semaphore {

	@Override
	public float vitesseApproche(Voiture v) 
	{
		return 0;
	}

}
