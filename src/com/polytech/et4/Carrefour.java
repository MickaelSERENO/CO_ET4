package com.polytech.et4;

import java.util.ArrayList;
import java.util.Random;

public class Carrefour extends Jonction {
	private ArrayList<SegmentRoute> listeSegmentRoute;
	
	//Liste des routes, position de la jonction sur la première route
	private Carrefour(ArrayList<SegmentRoute> lSegmentRoute, int sens)
	{
		listeSegmentRoute = lSegmentRoute;
		if(sens == SensDeplacement.ARRIERRE)
			lSegmentRoute.get(0).setJonctionDebut(this);
		else
			lSegmentRoute.get(0).setJonctionFin(this);

		for(int i=0; i < lSegmentRoute.size(); i++)
		{
			if(sens != SensDeplacement.ARRIERRE)
				lSegmentRoute.get(i).setJonctionFin(this);
			else if(sens != SensDeplacement.AVANT)
				lSegmentRoute.get(i).setJonctionDebut(this);
		}
	}
	
	//Liste des routes, position de la jonction sur la première route
	static public Carrefour makeCarrefour(ArrayList<SegmentRoute> lSegmentRoute, int sens) throws TableauNonCompatible
	{
		if(lSegmentRoute.size() < 2)
			throw new TableauNonCompatible();
		
		if(sens != SensDeplacement.ARRIERRE || sens != SensDeplacement.AVANT)
			throw new TableauNonCompatible();
		
		return new Carrefour(lSegmentRoute, sens);
	}

	public SegmentRoute getNextSegmentRoute(SegmentRoute origine)throws OrigineJonctionException{
		if(this.listeSegmentRoute.contains(origine))
		{
			ArrayList<SegmentRoute> listeTemp = (ArrayList<SegmentRoute>) listeSegmentRoute.clone();
			listeTemp.remove(origine);
			int size = listeSegmentRoute.size();
			Random rand = new Random();
			int indexNext = rand.nextInt(size);
			return listeTemp.get(indexNext);
		}
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
