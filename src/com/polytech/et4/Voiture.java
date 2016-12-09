package com.polytech.et4;

import com.polytech.et4.Updatable;
import com.polytech.et4.SegmentRoute;
import com.polytech.et4.Element;
import com.polytech.et4.SensDeplacement;

public class Voiture extends Element implements Updatable
{
	static private int identifiantCourant = 0;
	static private final int LONGUEUR = 1;

	private final int    m_vitesseMax;
	private int		     m_vitesseCourante;
	private int          m_identifiant;
    private SegmentRoute m_segmentRoute = null;

	public Voiture(int vitesseMax, SegmentRoute route, int position, int sens)
	{
		super(position, sens);
		m_vitesseMax      = vitesseMax;
		m_vitesseCourante = vitesseMax;
		m_identifiant     = identifiantCourant;

        m_segmentRoute    = route;
		identifiantCourant++;

	}

    public void prochaineEtape()
    {
        //On modifie la position sur la route ou la prochaine route que l'on souhaite
        m_position = m_position + ((m_sens == SensDeplacement.ARRIERRE) ? -1 : 1)*m_vitesseCourante;

		//On commit le déplacement. Normalement pas d'erreur
        SegmentRoute prochaineRoute=null;
        try
        {
        	prochaineRoute = m_segmentRoute.getNextSegmentRoute(this);
        }
        catch(PasBonneRoute e)
        {}
        
		//Redefini la position de la voiture si changement de route.
		if(prochaineRoute != m_segmentRoute)
		{
			m_position = (m_sens == SensDeplacement.ARRIERRE) ? prochaineRoute.getPositionFin() : prochaineRoute.getPositionDebut();
			m_segmentRoute = prochaineRoute;
		}

		//Le premier prochain obstacle déterminera notre vitesse de déplacement.
		for(int i=1; i <= m_vitesseCourante; i++)
		{
			Semaphore sema = prochaineRoute.getSemaphore(m_position + i*((m_sens == SensDeplacement.ARRIERRE) ? -1 : 1), m_sens);
			if(sema != null)
			{
				m_vitesseCourante *= sema.vitesseApproche(this);
				break;
			}
			
			//Si une voiture, on se met juste derrière elle.
			Voiture prochaineVoiture = prochaineRoute.getVoiture(m_position + i*((m_sens == SensDeplacement.ARRIERRE) ? -1 : 1), m_sens);
			if(prochaineVoiture != null)
				m_vitesseCourante = i-1;
		}
    }
    
    public int getVitesseCourante()
    {
    	return m_vitesseCourante;
    }

    public int getVitesseMax()
    {
    	return m_vitesseMax;
    }
    
	public SegmentRoute getSegmentRoute() {
		return m_segmentRoute;
	}
}
