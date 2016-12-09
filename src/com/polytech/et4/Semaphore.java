package com.polytech.et4;

import com.polytech.et4.Element;

public abstract class Semaphore extends Element implements Obstacle {
	
	private ElemRegulation er;

	@Override
	public boolean peutPasser() {
		return false;
	}
	
	public abstract float vitesseApproche(Voiture v);

}
