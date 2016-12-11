package com.polytech.et4;

import com.polytech.et4.ElemRegulationCapteur;

/* \class VitesseChangeTricolore
 * \brief Permet de gérer la couleur d'un seul feu en fonction d'un capteur de vitesse. Si le capteur de vitesse notifie l'objet, le feu passera à l'orange puis au rouge.*/
public class VitesseChangeBicolore extends ElemRegulationCapteur
{
	private final int MAX_TIMER_ROUGE  = 10;
	private boolean m_estNotifie       = false;
	private int     m_timer            = 0;
	private int     m_vitesseMax       = 0;
	private FeuBicolore m_feu;

	/* \brief Constructeur
	 * \param cv Le capteur de vitesse lié à l'objet*/
	public VitesseChangeBicolore(CapteurVitesse cv, FeuBicolore feu, int vitesseMax)
	{
		super(cv);
		m_feu = feu;
		cv.setElemRegulation(this);
		m_vitesseMax = vitesseMax;
	}

	public void notifie()
	{
		//On sait qu'on travaille sur un capteur de vitesse.
		if(!m_estNotifie && ((CapteurVitesse)m_capt).getVitesse() > m_vitesseMax)
		{
			m_feu.changerCouleur(Bicolore.ROUGE);
			m_estNotifie = true;
			m_timer = 0;
		}
	}

	public void prochaineEtape()
	{
		//Gère si la prochaine étape pour le feu est de passer au vert
		if(m_estNotifie)
		{
			if(m_timer >= MAX_TIMER_ROUGE)
			{
				m_timer      = 0;
				m_estNotifie = false;
				m_feu.changerCouleur(Tricolore.VERT);
			}
			else
				m_timer++;
		}
	}
}
