package com.polytech.et4;

import com.polytech.et4.Semaphore;
import com.polytech.et4.Voiture;

public class Stop extends Semaphore {

	public Stop(int sens, SegmentRoute r)
	{
		super(sens, r);
	}
	
	@Override
	public float vitesseApproche(Voiture v) 
	{
		//Ramener la voiture sur le feu stop, avec une vitesse de 0
		if(v.getVitesseCourante() != 0)
		{
			System.out.println("Teste vitesse approche. return : " + Math.min(Math.abs((v.getPosition() - this.getPosition() + (m_sens == SensDeplacement.ARRIERRE ? 1 : -1))/v.getVitesseMax()), 1));
			System.out.println("self position : " + m_position);
			if(m_sens == SensDeplacement.ARRIERRE)
				return Math.min((-this.getPosition() + v.getPosition() + 1)/(float)v.getVitesseMax(), 1.0f);
			else
				return Math.min((+this.getPosition() - v.getPosition() -1)/(float)v.getVitesseMax(), 1.0f);

		}
		else
			return 1.0f;
	}

	public boolean peutPasser(Voiture v)
	{
		//Peut passer si avant il était à vitesse nulle
		if(v.getPosition() == (m_position + ((m_sens == SensDeplacement.ARRIERRE) ? 1 : -1)) && v.getSens() == m_sens)
		{
			System.out.println("Au stop : " + v);
			return v.getVitesseCourante() == 0;
		}
		return true;
	}
}