package com.polytech.et4;

public class JonctionSimple extends Jonction {
	private Route route1;
	private Route route2;
	
	public JonctionSimple(Route r1, Route r2){
		route1 = r1;
		route2 = r2;
	}
	
	public Route getNextRoute(Route origine) throws OrigineJonctionException {
		if(origine.equals(this.route1))
			return this.route2;
		else if (origine.equals(this.route2))
			return this.route1;
		else
			throw new OrigineJonctionException();
	}
}