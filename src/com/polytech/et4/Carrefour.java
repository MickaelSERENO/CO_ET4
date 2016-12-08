package com.polytech.et4;

import java.util.ArrayList;
import java.util.Random;

public class Carrefour extends Jonction {
	private ArrayList<Route> listeRoute;
	
	public Carrefour(ArrayList<Route> lRoute){
		listeRoute = lRoute;
	}

	public Route getNextRoute(Route origine)throws OrigineJonctionException{
		if(this.listeRoute.contains(origine))
		{
			ArrayList<Route> listTemp = listeRoute;
			listeRoute.remove(origine);
			int size = listeRoute.size();
			Random rand = new Random();
			int indexNext = rand.nextInt(size);
			return listTemp.get(indexNext);
		}
		else
			throw new OrigineJonctionException();
}
