package com.polytech.et4;

import com.polytech.et4.Updatable;
import com.polytech.et4.SegmentRoute;

public class Voiture implements Updatable
{
	static private int identifiantCourant = 0;
	static private final int LONGUEUR = 1;

	private final int    m_vitesseMax;
	private int		     m_vitesseCourante;
	private int          m_identifiant;
    private SegmentRoute m_segmentRoute = null;
    private int          m_position = 0;
    private SensDeplacement m_sens;

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
        m_position = m_position + ((m_sens == ARRIERE) ? -1 : 1);

		//On commit le déplacement
        SegmentRoute prochaineRoute = m_segmentRoute.setPosition(this);

		//Redefini la position de la voiture si changement de route.
		if(prochaineRoute != m_segmentRoute)
		{
			m_position = (m_sens == ARRIERE) ? prochaineRoute.getPositionFin() : prochaineRoute.getPositionDebut();
			m_segmentRoute = prochaineRoute
		}

		Semaphore sema = prochaineRoute.getSemaphore(m_position);
		if(sema)
			m_vitesse *= sema.getRatioVitesse();
    }
}
