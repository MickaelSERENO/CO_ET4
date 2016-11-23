package com.polytech.et4;

import com.polytech.et4.ElemRegulation;

/* \class VitesseChangeTricolore
 * \brief Permet de gérer la couleur d'un seul feu en fonction d'un capteur de vitesse. Si le capteur de vitesse notifie l'objet, le feu passera à l'orange puis au rouge.*/
public class VitesseChangeTricolore extends ElemRegulation
{
	public int final MAX_TIMER_ORANGE = 2;
	public int final MAX_TIMER_ROUGE  = 10;
	public boolean m_estNotifie       = false;
	public int     m_timer            = 0;
	public FeuTricolore m_feu;

	/* \brief Constructeur
	 * \param cv Le capteur de vitesse lié à l'objet*/
	public VitesseChangeTricolore(CapteurVitesse cv, FeuTricolore feu)
	{
		m_feu = feu;
		cv.setElemRegulation(this);
	}

	public void etreNotifie()
	{
		if(!m_estNotifie)
		{
			m_feu.changerCouler(Tricolore.ORANGE);
			m_estNotifie = true;
			m_timer = 0;
		}
	}

	public void prochaineEtape()
	{
		//Gère si la prochaine étape pour le feu est de passer au rouge
		if(m_estNotifie)
		{
			if(m_timer >= MAX_TIMER_ORANGE)
			{
				m_timer      = 0;
				m_estNotifie = false;
				m_feu.changerCouleur(Tricolore.ROUGE);
			}
			else
				m_timer++;
		}

		//Gère si la prochaine étape pour le feu est de passer au vert.
		else if(m_feu.getCouleur() == ROUGE)
		{
			if(m_timer >= MAX_TIMER_ROUGE)
			{
				m_timer = 0;
				m_feu.changerCouleur(Tricolore.VERT);
			}
			else
				m_timer++;
		}
	}
}
