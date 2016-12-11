package com.polytech.et4;

import java.util.ArrayList;
import java.util.Scanner;

public class Main 
{
	public static void main(String[] argv)
	{
		//On créé les tableaux nécessaires
		ArrayList<Capteur> lcapteurs           = new ArrayList<Capteur>();
		ArrayList<Voiture> lvoitures           = new ArrayList<Voiture>();
		ArrayList<Semaphore> lsemaphores       = new ArrayList<Semaphore>();
		ArrayList<SegmentRoute> lsegmentRoutes = new ArrayList<SegmentRoute>();
		ArrayList<Jonction> ljonctions         = new ArrayList<Jonction>();
		ArrayList<ElemRegulation> lelementsRegulation = new ArrayList<ElemRegulation>();
		
		//On commence par définir certaines routes
		lsegmentRoutes.add(new SegmentRoute(100));
		lsegmentRoutes.add(new SegmentRoute(50));
		
		//On les relis par 3 Jonctions
		ljonctions.add(new Barriere(lsegmentRoutes.get(0), SensDeplacement.ARRIERRE));
		ljonctions.add(new Barriere(lsegmentRoutes.get(1), SensDeplacement.AVANT));
		ljonctions.add(new JonctionSimple(lsegmentRoutes.get(0), lsegmentRoutes.get(1)));
		
		//On créé ensuite 4 semaphores. On peut utiliser les semaphores vides si on le souhaite
		lsemaphores.add(new SemaphoreVide(SensDeplacement.ARRIERRE, lsegmentRoutes.get(0)));
		FeuTricolore f1 = new FeuTricolore(SensDeplacement.AVANT, lsegmentRoutes.get(0)); 
		lsemaphores.add(f1);
		lsemaphores.add(new SemaphoreVide(SensDeplacement.ARRIERRE, lsegmentRoutes.get(1)));
		lsemaphores.add(new Stop(SensDeplacement.AVANT, lsegmentRoutes.get(1)));
		
		ArrayList<FeuTricolore> feux = new ArrayList<FeuTricolore>();
		feux.add(f1);
		try
		{
			CarrefourTricoloreSimple elem1 = CarrefourTricoloreSimple.makeCarrefourTricoloreSimple(feux);
			lelementsRegulation.add(elem1);
		}
		catch(TableauVideException e)
		{}
		
		//Le schéma donne donc
		//Barriere SemaphoreVide ---98 unité --- FeuTricolore -- JonctionSimple -- SemaphoreVide -- 48 unités -- Stop -- Barriere
		
		try
		{
			lvoitures.add(Voiture.makeVoiture(10, lsegmentRoutes.get(0), 1, SensDeplacement.AVANT));
		}
		catch(PasBonneRoute r)
		{}
		
		System.out.println("Appuyez sur entré pour passer à la prochaine étapes");
		Scanner s = new Scanner(System.in);
		
		while(s.nextLine() != null)
		{
			for(ElemRegulation e : lelementsRegulation)
				e.prochaineEtape();
			
			for(Voiture v : lvoitures)
			{
				v.prochaineEtape();
				System.out.println(v);
			}
			
			for(Capteur c : lcapteurs)
				c.prochaineEtape();
		}
		s.close();
	}
}
