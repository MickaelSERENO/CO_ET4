package com.polytech.et4;

import com.polytech.et4.ElemRegulation;
import com.polytech.et4.FeuTricolore;

public class CarrefourTricoloreSimple extends ElemRegulation
{
	//Définie le prochain état à modifier
	public enum EtatFeuxTricolore
	{
		VERS_ORANGE, //Pour faire passer un feu à l'orange
		VERS_ROUGE, //Pour faire passer un feu au rouge
		VERS_VERT   //Pour faire passer un feu au vert
	}

	//On pourrait imaginer que la classe prenne des attributs pour définir les butées des timers.
	private int final MAX_ORANGE = 10;
	private int final MAX_ROUGE  = 10;
	private int final MAX_VERT   = 2;

	//Nos feux
	private ArrayList<? extends FeuTricolore> m_feux;
	private int m_timer=0;
	//Le feu qui doit être modifié
	private int m_idCourant=0;
	//Qu'est-ce qu'on fait quand le timer a atteint sa valeur.
	private EtatFeuxTricolore m_etat=VERS_ORANGE;

	//Ce constructeur ne sera appeler que par une fonction static
	private CarrefourTricoloreSimple(ArrayList<? extends FeuTricolore> feux)
	{
		m_feux = feux;
		for(Feu feu : m_feux)
			feu.changerCouleur(ROUGE);
		feux.get(0).changerCouleur(VERT);
	}

	public CarrefourTricoloreSimple(ArrayList<? extends FeuTricolore> feux) throws TableauVideException
	{
		return (feux.empty()) ? new	TableauVideException() : new CarrefourTricoloreSimple(feux);
	}

	public void prochaineEtape()
	{
		m_timer++;
		//Fait la rotation des couleurs si le timer correspondant est correct
		switch(m_etat)
		{
			case VERS_ORANGE:
				if(m_timer == MAX_ORANGE)
				{
					m_feux.get(m_idCourant).changerCouleur(Tricolore.ORANGE);
					m_etat = VERS_ROUGE;
					m_timer=0;
				}
				break;

			case VERS_ROUGE:
				if(m_timer == MAX_ROUGE)
				{
					m_feux.get(m_idCourant).changerCouleur(Tricolore.ROUGE);
					m_etat = VERS_VERT;
					m_idCourant = (m_idCourant+1)%m_feux.size();
					m_timer=0;
				}
				break;

			case VERS_VERT:
				if(m_timer == MAX_VERT)
				{
					m_etat = VERS_ORANGE;
					m_feux.get(m_idCourant).changerCouleur(Tricolore.VERT);
					m_timer=0;
				}
				break;

		}
	}
}
