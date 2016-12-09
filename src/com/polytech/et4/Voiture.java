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
    private int          m_position = 0;
    private int          m_sens;

	public Voiture(int vitesseMax, SegmentRoute route, int position, SensDeplacement sens)
	{
		m_vitesseMax      = vitesseMax;
		m_vitesseCourante = vitesseMax;
		m_identifiant     = identifiantCourant;
		identifiantCourant++;

        m_segmentRoute = route;
        m_position     = position;
        m_sens         = sens;
	}

    public void prochaineEtape()
    {
        //On modifie la position sur la route ou la prochaine route que l'on souhaite
        m_position = m_position + ((m_sens == SensDeplacement.ARRIERRE) ? -1 : 1)*m_vitesseCourante;

		//On commit le déplacement
        SegmentRoute prochaineRoute = m_segmentRoute.setPosition(this, m_position);

		//Redefini la position de la voiture si changement de route.
		if(prochaineRoute != m_segmentRoute)
		{
			m_position = (m_sens == SensDeplacement.ARRIERRE) ? prochaineRoute.getPositionFin() : prochaineRoute.getPositionDebut();
			m_segmentRoute = prochaineRoute
		}

		//Le premier prochain obstacle déterminera notre vitesse de déplacement.
		for(int i=1; i <= m_vitesseCourante; i++)
		{
			Semaphore sema = prochaineRoute.getSemaphore(m_position + i*((m_sens == SensDeplacement.ARRIERRE) ? -1 : 1), m_sens);
			if(sema)
			{
				m_vitesseCourante *= sema.getVitesse(this);
				break;
			}
			
			//Si une voiture, on se met juste derrière elle.
			Voiture prochaineVoiture = prochaineRoute.getVoiture(m_position + i*((m_sens == SensDeplacement.ARRIERRE) ? -1 : 1), m_sens);
			if(prochaineVoiture)
				m_vitesseCourante = i-1;
		}
    }
    
    public int getVitesseCourante()
    {
    	return m_vitesseCourante;
    }
}
