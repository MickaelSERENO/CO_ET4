package com.polytech.et4;

public class Barriere extends Jonction {
	private SegmentRoute segmentRoute1;
	
	//Jonction en fin ou début de segment ?
	public Barriere(SegmentRoute r1, int sens){
		segmentRoute1 = r1;
		if(sens == SensDeplacement.ARRIERRE)
			r1.setJonctionDebut(this);
		else
			r1.setJonctionFin(this);
	}
	
	public SegmentRoute getNextSegmentRoute(SegmentRoute origine) throws OrigineJonctionException{
		if(origine.equals(this.segmentRoute1))
			return null; //Signifie qu'il n'y a pas d'autre SegmentRoute après
		else
			throw new OrigineJonctionException();
	}
	
	public boolean peutPasser(Voiture v){
		try{
			if(this.getNextSegmentRoute(v.getSegmentRoute())!=null)
				return true;
		} catch(OrigineJonctionException e) {
			return false;
		}
		return false;
	}
}