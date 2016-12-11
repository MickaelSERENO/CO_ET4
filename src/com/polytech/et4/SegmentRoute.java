package com.polytech.et4;

import java.util.ArrayList;

public class SegmentRoute 
{
	private Jonction jonction1; //avant
	private Jonction jonction2; //arrière
	private ArrayList<Voiture> listeVoiture;
	private ArrayList<Capteur> listeCapteur;
	private Semaphore semaphore1; //avant
	private Semaphore semaphore2; //arrière
	private int taille;

	private int identifiant;
	private static int identCourant=0;
	
	//On ne prends pas de paramêtre car étape de construction oblige à créer des Segments de route avant les Jonctions et les Semaphores.
	public SegmentRoute(int t)
	{
		jonction1 = null;
		jonction2 = null;
		semaphore1 = null;
		semaphore2 = null;	
		
		listeVoiture = new ArrayList<Voiture>();
		listeCapteur = new ArrayList<Capteur>();
		
		taille = t;
		identifiant = identCourant+1;
		identCourant++;
	}
	
	public Voiture getVoiture(int position, int sens)
	{
		for(Voiture v : listeVoiture)
		{
			if(v != null && v.getPosition()==position)
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
		if(semaphore1 != null && semaphore1.getPosition()==position)
		{
			if(semaphore1.getSens()==sens)
			{
				return semaphore1;
			}
		}
		if(semaphore2 != null && semaphore2.getPosition()==position)
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
		if(v.getPosition() < getPositionDebut()) //voiture en adébut de route = avant
		{
			if(v.getSens()==SensDeplacement.ARRIERRE)
			{
				try {
					if(jonction1 != null)
						return jonction1.getNextSegmentRoute(this);
				} catch (OrigineJonctionException e) {
					return null;
				}
			}
		}
		
		if(v.getPosition() > getPositionFin()) //voiture en fin de route = arriere
		{
			if(v.getSens()==SensDeplacement.AVANT)
			{
				try {
					if(jonction2 != null)
						return jonction2.getNextSegmentRoute(this);
				} catch (OrigineJonctionException e) {
					System.out.println("erreur !!!, la jonction ne m'appartient pas (devrait jamais arriver)");
					return null;
				}
			}
		}
		
		return null;
	}
	
	public int getPositionFin()
	{
		return taille-1;
	}
	
	public int getPositionDebut()
	{
		return 0;
	}
	
	public void supprimerVoiture(Voiture v)
	{
		listeVoiture.remove(v);
	}
	
	public void ajouterVoiture(Voiture v)
	{
		if(listeVoiture.contains(v))
			return;
		listeVoiture.add(v);
	}

	public void ajouterCapteur(Capteur c)
	{
		if(listeCapteur.contains(c))
			return;
		listeCapteur.add(c);
		c.setSegmentRoute(this);
	}

	public String toString()
	{
		return "Segment de route numéro " + identifiant;
	}

	public void commitDeplacement(Voiture v)
	{
		//On trouve le capteur dans notre position et on lui prévient qu'une voiture vient d'arriver
		for(Capteur c : listeCapteur)
		{
			if(c.getPosition() == v.getPosition() && c.getSens() == v.getSens())
			{
				c.notifie(v);
				break;
			}
		}
		
		ArrayList<Obstacle> listeObstacle = new ArrayList<Obstacle>();

		//On ajoute les jonctions au test que si nécessaire
		if(v.getPosition() == getPositionDebut()-1)
			listeObstacle.add(jonction1);
		else if(v.getPosition() == getPositionFin()+1)
			listeObstacle.add(jonction2);

		//Les semaphores ayant des calculs plus complexes, nous les ajoutons tous le temps
		listeObstacle.add(semaphore1);
		listeObstacle.add(semaphore2);
	
		for(Obstacle o : listeObstacle)
			if(o != null && !o.peutPasser(v))
			{
				System.out.println(o);
				notifieVoitureDangereuse(v);	
			}

		for(Voiture v2 : listeVoiture)
		{
			if(!v2.peutPasser(v)) //Le différent est justifié car les voitures sont toutes différentes de part leur identifiants uniques
			{
				notifieVoitureDangereuse(v);
			}
		}
	}

	public void notifieVoitureDangereuse(Voiture v)
	{
		//On peut supposer un autre comportement, ici un print fera l'affaire pour la démonstration
		System.out.println("La voiture " + v + " n'avait pas le droit de passer. Danger ! ");
	}
	
	public void setSemaphore(Semaphore sema, int sens)
	{
		if(sens == SensDeplacement.ARRIERRE)
			semaphore1=sema;
		else
			semaphore2 = sema;
	}
	
	public void setJonctionDebut(Jonction j)
	{
		System.out.println("Debut " + j + " " + identifiant);
		jonction1 = j;
	}
	
	public void setJonctionFin(Jonction j)
	{
		System.out.println("Fin " + j + " " + identifiant);
		jonction2 = j;
	}
	
	public void risqueCollision(Voiture v)
	{
		System.out.println("Un risque de collision est intervenu, attention ! Voiture : " + v); //On pourrait imaginer autre choses.
	}
}
