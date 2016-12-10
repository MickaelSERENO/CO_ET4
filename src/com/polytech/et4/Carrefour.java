package com.polytech.et4;

import java.util.ArrayList;
import java.util.Random;

public class Carrefour extends Jonction {
	private ArrayList<SegmentRoute> listeSegmentRoute;
	
	public Carrefour(ArrayList<SegmentRoute> lSegmentRoute){
		listeSegmentRoute = lSegmentRoute;
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
