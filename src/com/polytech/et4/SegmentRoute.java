package com.polytech.et4;

import java.util.ArrayList;

public class SegmentRoute {
	private Jonction jonction1; //avant
	private Jonction jonction2; //arrière
	private ArrayList<Voiture> listeVoiture;
	private ArrayList<Capteur> listeCapteur;
	private Semaphore semaphore1; //avant
	private Semaphore semaphore2; //arrière
	private int taille;
	
	
	public SegmentRoute(Jonction j1, Jonction j2, Semaphore s1, Semaphore s2, ArrayList<Voiture> lVoiture, ArrayList<Capteur> lCapteur, int t)
	{
		jonction1 = j1;
		jonction2 = j2;
		semaphore1 = s1;
		semaphore2 = s2;
		listeVoiture = lVoiture;
		listeCapteur = lCapteur;
		taille = t;
	}
	
	public Voiture getVoiture(int position, int sens)
	{
		for(Voiture v : listeVoiture)
		{
			if(v.getPosition()==position)
			{
				if(v.getSens()==sens)
				{
					return v;
				}
			}
		}
		return null;
	}
	
	public Semaphore getSemaphore(int position, int sens)
	{
		if(semaphore1.getPosition()==position)
		{
			if(semaphore1.getSens()==sens)
			{
				return semaphore1;
			}
		}
		if(semaphore2.getPosition()==position)
		{
			if(semaphore2.getSens()==sens)
			{
				return semaphore2;
			}
		}
		return null;
	}
	
	public SegmentRoute getNextSegmentRoute(Voiture v) throws PasBonneRoute
	{
		if(v.getPosition()==0) //voiture en adébut de route = avant
		{
			if(v.getSens()==SensDeplacement.AVANT)
			{
				try {
					return jonction1.getNextSegmentRoute(this);
				} catch (OrigineJonctionException e) {
					return null;
				}
			}
		}
		if(v.getPosition()==taille) //voiture en fin de route = arriere
		{
			if(v.getSens()==SensDeplacement.ARRIERRE)
			{
				try {
					return jonction2.getNextSegmentRoute(this);
				} catch (OrigineJonctionException e) {
					return null;
				}
			}
		}
		return null;
		
		
	}
	
	public int getPositionFin()
	{
		return taille;
	}
	
	public int getPositionDebut()
	{
		return 0;
	}
}