package com.polytech.et4;

import com.polytech.et4.Updatable;
import com.polytech.et4.SegmentRoute;
import com.polytech.et4.Element;
import com.polytech.et4.SensDeplacement;

public class Voiture extends Element implements Updatable, Obstacle
{
	static private int identifiantCourant = 0;
	static private final int LONGUEUR = 1;

	private final int  m_vitesseMax;
	private int	       m_vitesseCourante;
	private int        m_identifiant;

	private Voiture(int vitesseMax, SegmentRoute route, int position, int sens)
	{
		super(position, sens, route);
		m_vitesseMax      = (int)vitesseMax;
		m_vitesseCourante = (int)vitesseMax;
		m_identifiant     = identifiantCourant;

		identifiantCourant++;
		route.ajouterVoiture(this);
	}
	
	public static Voiture makeVoiture(int vitesseMax, SegmentRoute route, int position, int sens) throws PasBonneRoute
	{
		if(position < route.getPositionDebut() || position > route.getPositionFin())
			throw new PasBonneRoute("La position n'est pas dans la route");
		return new Voiture(vitesseMax, route, position, sens);
	}

    public void prochaineEtape()
    {
		setPosition(m_position + (m_sens == SensDeplacement.ARRIERRE ? -1 : 1) * m_vitesseCourante);
    }
    
    public int getVitesseCourante()
    {
    	return (int)m_vitesseCourante;
    }

    public int getVitesseMax()
    {
    	return (int)m_vitesseMax;
    }
    
	public SegmentRoute getSegmentRoute() {
		return m_segmentRoute;
	}
	
	public void setSegmentRoute(SegmentRoute s)
	{
		if(s != m_segmentRoute)
		{
			m_segmentRoute = s;
		}
	}

	//Nécessaire de redéfinir car faut prendre en compte la route.
	public void setPosition(int position)
	{
		if(m_segmentRoute == null)
			return;
		
		if(position != m_position)
		{
    	int deplacement = Math.abs(m_position - position);
		int signe = 1;
		m_sens = SensDeplacement.AVANT;
		if(m_position > position)
		{
			signe = -1;
			m_sens = SensDeplacement.ARRIERRE;
		}

		//On commit chaque pas de déplacement
		for(int i=0; i < deplacement; i++)
		{
			m_position += signe;
 
			if(m_sens == SensDeplacement.ARRIERRE && m_position < m_segmentRoute.getPositionDebut() || m_sens == SensDeplacement.AVANT && m_position > m_segmentRoute.getPositionFin())
			{
				//On commit le déplacement. Normalement pas d'erreur
				SegmentRoute prochaineRoute=null;
				try
				{
					prochaineRoute = m_segmentRoute.getNextSegmentRoute(this);
					System.out.println("ProchaineRoute " + m_segmentRoute.getNextSegmentRoute(this));
				}
				catch(PasBonneRoute e)
				{}
				
				//Redefini la position de la voiture si changement de route.
				if(prochaineRoute != m_segmentRoute)
				{
					m_segmentRoute.supprimerVoiture(this);
					if(prochaineRoute != null)
					{
						m_position = (m_sens == SensDeplacement.ARRIERRE) ? prochaineRoute.getPositionFin() : prochaineRoute.getPositionDebut();
						m_segmentRoute = prochaineRoute;
						m_segmentRoute.ajouterVoiture(this);
					}
					else
					{
						System.out.println("On annuler !");
						m_position = m_position + (m_sens == signe ? 1 : -1); //On annule le déplacement
						return;
					}
				}
			}

			m_segmentRoute.commitDeplacement(this);
    	}
		}

		//On détermine notre vitesse par rapport à ce qui arrivera après
		int i=1;
		int v = m_vitesseMax; //On souhaiterai toujours redémarré
		while(i <= v)
		{
			for(; i <= v; i++)
			{
				Semaphore sema = m_segmentRoute.getSemaphore(m_position + i*((m_sens == SensDeplacement.ARRIERRE) ? -1 : 1), m_sens);
				if(sema != null)
				{
					m_vitesseCourante = (int)(m_vitesseMax * sema.vitesseApproche(this));
					v = m_vitesseCourante;
					System.out.println(""+sema.vitesseApproche(this));
					i++;
					break;
				}
			
				//Si une voiture, on se met juste derrière elle.
				Voiture prochaineVoiture = m_segmentRoute.getVoiture(m_position + i*((m_sens == SensDeplacement.ARRIERRE) ? -1 : 1), m_sens);
				if(prochaineVoiture != null)
				{
					m_vitesseCourante = i-1;
					v = m_vitesseCourante;
				}
			}
		}
	}

	public boolean peutPasser(Voiture v)
	{
		if(v != this && v.m_sens == m_sens && v.m_position == m_position)
			return false;
		return true;
	}

	public String toString()
	{
		return "Voiture n°" + m_identifiant + " a la position " + m_position + " Sur la route " + m_segmentRoute;
	}
}
