package com.polytech.et4;

public class Barriere extends Jonction {
	private Route route1;
	
	public Barriere(Route r1){
		route1 = r1;
	}
	
	public Route getNextRoute(Route origine) throws OrigineJonctionException{
		if(origine.equals(this.route1))
			return null;
		else
			throw new OrigineJonctionException();
}
