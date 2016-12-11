package com.polytech.et4;
import com.polytech.et4.ElemRegulationCapteur;

public abstract class Capteur extends Element implements Updatable
{
    protected ElemRegulationCapteur m_elem=null;
	protected Voiture m_voitureCourante = null;
    
    public Capteur(int position, int sens, SegmentRoute r)
    {
    	super(position, sens, r);
    }
    
    public ElemRegulationCapteur getElemRegulation() 
    {
        return m_elem;
    }
    
    public void setElemRegulation(ElemRegulationCapteur m_elem) 
    {
        this.m_elem = m_elem;
    }    

	public void setSegmentRoute(SegmentRoute r)
	{
		if(m_segmentRoute != r)
		{
			m_segmentRoute.ajouterCapteur(this);
			m_segmentRoute = r;
		}
	}

	public void prochaineEtape()
	{
		m_voitureCourante = null;
	}

	public void notifie(Voiture v)
	{
		//Si on avait déjà une voiture, collision
		if(m_voitureCourante != null)
			m_segmentRoute.risqueCollision(v);
		m_voitureCourante = v;
		if(m_elem != null)
			m_elem.notifie();
	}
}
