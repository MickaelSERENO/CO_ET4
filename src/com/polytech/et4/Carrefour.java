package com.polytech.et4;

import java.util.ArrayList;
import java.util.Random;

public class Carrefour extends Jonction {
	private ArrayList<SegmentRoute> listeSegmentRoute;
	
	private Carrefour(ArrayList<SegmentRoute> lSegmentRoute, ArrayList<Integer> lsens)
	{
		listeSegmentRoute = lSegmentRoute;
		for(int i=0; i < lsens.size(); i++)
		{
			if(lsens.get(i).intValue()  != SensDeplacement.ARRIERRE)
				lSegmentRoute.get(i).setJonctionDebut(this);
			else if(lsens.get(i).intValue() != SensDeplacement.AVANT)
				lSegmentRoute.get(i).setJonctionFin(this);
		}
	}
	
	public Carrefour makeCarrefour(ArrayList<SegmentRoute> lSegmentRoute, ArrayList<Integer> lsens) throws TableauNonCompatible
	{
		if(lsens.size() != lSegmentRoute.size())
			throw new TableauNonCompatible();
		
		for(Integer i : lsens)
			if(i.intValue() != SensDeplacement.ARRIERRE || i.intValue() != SensDeplacement.AVANT)
				throw new TableauNonCompatible();
		
		return new Carrefour(lSegmentRoute, lsens);
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
