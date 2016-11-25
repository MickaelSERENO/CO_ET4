package com.polytech.et4;

import com.polytech.et4.ElemRegulationCapteur;

/* \class VitesseChangeTricolore
 * \brief Permet de gérer la couleur d'un seul feu en fonction d'un capteur de vitesse. Si le capteur de vitesse notifie l'objet, le feu passera à l'orange puis au rouge.*/
public class VitesseChangeBicolore extends ElemRegulationCapteur
{
	private int final MAX_TIMER_ROUGE  = 10;
	private boolean m_estNotifie       = false;
	private int     m_timer            = 0;
	private FeuTricolore m_feu;

	/* \brief Constructeur
	 * \param cv Le capteur de vitesse lié à l'objet*/
	public VitesseChangeTricolore(CapteurVitesse cv, FeuTricolore feu)
	{
		super(cv);
		m_feu = feu;
		cv.setElemRegulation(this);
	}

	public void etreNotifie()
	{
		if(!m_estNotifie)
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
