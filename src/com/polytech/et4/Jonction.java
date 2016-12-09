package com.polytech.et4;

public abstract class Jonction implements Obstacle{

	//donne aleatoirement la SegmentRoute suivante
	abstract public SegmentRoute getNextSegmentRoute(SegmentRoute origine)throws OrigineJonctionException;
	
	abstract public boolean peutPasser(Voiture v);
}
